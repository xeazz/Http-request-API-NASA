# Чтение данных API NASA (HTTP-запрос)
**Цель:** с помощью публичного API NASA,необходимо скачать ежедневно выгружаемые им изображение или другой контент (например видео). 

Для этого необходимо:
1. Получить ключ для API NASA по адресу: https://api.nasa.gov/
2. Сделать запрос из кода: https://api.nasa.gov/planetary/apod?api_key=ВАШ_КЛЮЧ
3. Создать класс ответа и разобрать json-ответ с помощью Jackson или Gson
4. Найти поле url в ответе и скачать массив byte, который необходимо сохранить в файл.
5. Имя файла должно быть взято из части url.


Используемые зависимости:

    implementation group: 'org.apache.httpcomponents', name: 'httpclient', version: '4.5'
    implementation 'com.google.code.gson:gson:2.8.2'
    implementation 'com.googlecode.json-simple:json-simple:1.1.1'