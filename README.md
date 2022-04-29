## Introduction:
Back-end project of xunwu online shopping mall system, based on Springboot + JPA, including user information, commodity release, commodity display, shopping cart and other modules. <br>
Front-end project: https://github.com/Freyyyyyyy/xun-wu-front

## Development Environment
| |version|download|
|:---|:---|:---|
|JDK|11|https://www.oracle.com/java/technologies/downloads/#java11|
|Mysql|8.0|https://dev.mysql.com/downloads/installer/|
|Nginx|1.20.2|http://nginx.org/en/download.html|

## Directory
>─ src
>>─ main
>>>─ java\com\cpt202\xunwu
>>>>─ bean	&emsp;&emsp;//DTO <br>
>>>>─ controller	&emsp;&emsp;//controller layer <br>
>>>>─ model	&emsp;&emsp;//Entity <br>
>>>>─ property	&emsp;&emsp;//properties configuration <br>
>>>>─ repository	&emsp;&emsp;//data access interface <br>
>>>>─ service	&emsp;&emsp;//service layer <br>

## Deployment
### Local Deployment
#### Visual Studio Code
* Install: https://code.visualstudio.com/Download
* Add the following plugins: <br>
`Extension Pack for Java` <br>
<img src="https://user-images.githubusercontent.com/103989093/166079002-a5899888-8b8d-42b1-a32a-3e94be919090.png" width=400px> <br>
`Spring Boot Dashboard` <br>
<img src="https://user-images.githubusercontent.com/103989093/166079171-35a65071-c0ce-4d72-b5f3-f571280dc389.png" width=400px> <br>
`Spring Initializr Java Support` <br>
<img src="https://user-images.githubusercontent.com/103989093/166079223-dd23c0d1-b471-43eb-87b5-e71d04fd526d.png" width=400px> <br>
* Clone the project and Open <br>
#### MySQL
* Install MySQL8.0: https://dev.mysql.com/downloads/installer/
* Set account and password
* CREATE SCHEMA 'xunwu'
* Open `xun-wu/src/main/resources/application.properties` <br>
Modify `url` to `jdbc:mysql://localhost:3306/xunwu` <br>
Modify `username` and `password` to the username and password of your local database account <br>
![image](https://user-images.githubusercontent.com/103989093/166080040-c8bc7828-88fa-46a4-891b-7fb1ce0812d4.png)
#### Initiate
* Run the `main` fuction in `com.cpt202.xunwu.XunWuApplication`
