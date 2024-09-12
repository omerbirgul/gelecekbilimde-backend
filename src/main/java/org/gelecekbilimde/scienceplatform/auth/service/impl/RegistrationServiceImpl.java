package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.AlreadyRegisteredException;
import org.gelecekbilimde.scienceplatform.auth.exception.DefaultRoleNotDefinedException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserVerificationAlreadyCompletedException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserVerificationIsNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.VerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.auth.service.RegistrationService;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserVerificationEntity;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserStatus;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserVerificationStatus;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserVerificationRepository;
import org.gelecekbilimde.scienceplatform.user.service.UserEmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
class RegistrationServiceImpl implements RegistrationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final UserEmailService userEmailService;
	private final UserVerificationRepository userVerificationRepository;

	@Override
	@Transactional
	public void register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new AlreadyRegisteredException(request.getEmail());
		}

		RoleEntity roleEntity = roleRepository.getByIsDefaultTrue()
			.orElseThrow(DefaultRoleNotDefinedException::new);

		UserEntity userEntity = UserEntity.builder()
			.id(RandomUtil.generateUUID())
			.firstName(request.getFirstname())
			.lastName(request.getLastname())
			.email(request.getEmail())
			.birthDate(request.getBirthDate())
			.biography(request.getBiography())
			.gender(request.getGender())
			.degree(request.getDegree())
			.roleEntity(roleEntity)
			.roleId(roleEntity.getId())
			.status(UserStatus.NOT_VERIFIED)
			.password(passwordEncoder.encode(request.getPassword()))
			.build();

		userRepository.save(userEntity);


		UserVerificationEntity userVerificationEntity = UserVerificationEntity.builder()
			.userId(userEntity.getId())
			.status(UserVerificationStatus.WAITING)
			.build();
		userVerificationRepository.save(userVerificationEntity);

		CompletableFuture.runAsync(() -> userEmailService.sendVerifyMessage(userEntity.getEmail(), userVerificationEntity.getId()));
	}

	@Override
	@Transactional
	public void verify(VerifyRequest verifyRequest) {

		UserVerificationEntity userVerificationEntity = userVerificationRepository
			.findById(verifyRequest.getVerificationId())
			.orElseThrow(() -> new UserVerificationIsNotFoundException(verifyRequest.getVerificationId()));

		if (userVerificationEntity.isCompleted()) {
			throw new UserVerificationAlreadyCompletedException();
		}

		userVerificationEntity.complete();
		userVerificationRepository.save(userVerificationEntity);


		UserEntity userEntity = userRepository.findById(userVerificationEntity.getUserId())
			.orElseThrow(UserNotFoundException::new);

		userEntity.verify();
		userRepository.save(userEntity);

		CompletableFuture.runAsync(() -> userEmailService.sendWelcomeMessage(userEntity.getEmail()));
	}

}
