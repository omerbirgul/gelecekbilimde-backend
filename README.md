# Gelecek Bilimde Topluluğu Bilim İletişimi Platformu [![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)

Gelecek bilimde topluluğu, Bilim iletişimi 

## Lokal Ortam Kurulumu

.env içerisindeki bilgileri lokal değişkenlere koyduktan sonra projeyi docker da veya yerel kurulumlar ile proje çalıştırılabilir.

###  ![Elasticsearch](https://img.shields.io/badge/Elasticsearch-=&nbsp;&nbsp;&nbsp;v8.1.0-green) 
###  ![Redis](https://img.shields.io/badge/Redis-=&nbsp;&nbsp;v6.2.7-green)
###  ![Postgres](https://img.shields.io/badge/Postgres-=&nbsp;&nbsp;v15.3-green)
###  ![Docker](https://img.shields.io/badge/Docker-=&nbsp;&nbsp;v20.10.22-green) ![Docker Compose](https://img.shields.io/badge/Docker&nbsp;Compose-=&nbsp;&nbsp;v2.15.1-green)

```bash
  docker compose build
```

```bash
  docker compose up
```


ENV Yapılandırma

```bash
  cp resources/dev.properties.example dev.properties
```
dev.properties dosyasını kopyaladıktan sonra resource olarak serve edilecek klsörün bulunduğu dizin application.yml dosyasının env değerine yazılır.
ardından dev.properties bilgileri doldurulduktan sonra proje run edilir.

db seeding 
resources/db/seed.sql çalıştırılmalı

## İlişkili Postman Collection
https://www.postman.com/gelecek-bilimde-team/workspace/gelecek-bilimde/collection/37702250-8bfe54f1-1864-410a-b960-ab4e7122dd3e?action=share&creator=37702250

## İlişkili Projeler

[Frontend](https://github.com/gelecekbilimde/gelecek-bilimde-frontend)

[Android](https://github.com/gelecekbilimde/Android-Application)


## Commit Mesajları
### Git mejlarında bir starndartda yazmak için en başına commit ne ile alakalı ise onun başlığını yazarız. (feat,imp,fix) Genellikle bir commitin içerisnde tek tip başlık olması daha düzenli versiyon yönetimi için önemlidir.
```text
feat     : Yeni bir özellik geliştirildiğini bildirmek için. Açılımı : feature 
imp      : Var olan bir özellik üzerinde refactor yapıldıysa veya ek bir alt özellik geliştirildiyse kullanılır. Açılımı : improve
fix      : Var olan bir özellikte hata düzeltmelerini belirtmek için (hotfix gibi durumlarıda içerir) Açılımı:fix
refactor : Var olan bir geliştirme içerisinde kod iyileştirmesi yapıldığını belirtmek için kullanılır. Açılımı: refactor
```

### Örnek Git Mesajı;
```text
feat : Post servisi geliştirldi
imp  : Api dönüşlerine timestamp eklendi
fix  : Kullanıcı kendi yorumunu düzenlerken yetki hatası sorunu düzeltildi
```

## Örnek Api Dönüşleri 
### Başarılı (200 , 201, 207)
``` json
{
    "statusText": "OK",
    "status": 200,
    "list": {
        "message": "API version: 0.0.1"
    },
    "count": 1,
    "timestamp": "2023-05-31 21:58:45",
    "request": {
        "args": {
            "v":"v1"
        },
        "path": "http://localhost:8057/api/version",
        "params": {
            "param1": "44",
            "param2": "123"
        }
    },
    "responseCode": "43e9f812-8a7c-49f9-812e-024ee0705f9b"
}
```
### Başarısız (400, 401, 404, 405, 422, 500, 503)
``` json
{
    "path": "/api/version",
    "message": "Versiyon Alınamadı",
    "status": 400,
    "statusText": "BAD_REQUEST",
    "method": "GET",
    "args": {},
    "errorCode": "0ada5a5c-6834-4df6-9452-ddf6c64b03a8",
    "timestamp": "2023-05-31 22:19:07"
}
```
