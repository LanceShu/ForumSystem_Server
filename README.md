# ForumSystem_Server
*基于前端与后台的论坛系统（Server端）*

### 用户功能：
#### 1. 注册（register）：

- URL：http://123.207.145.251:8080/ForumSystem/register

- 参数（POST）：

|参数名|参数类型|参数说明|
|:------:|:--------:|:--------:|
|username|String|用户名(not null)|
|password|String|密码(not null)|
|gender|String|性别|

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
FormBody formBody = new FormBody.Builder()
            .add("username", "test")
            .add("password", "test")
            .add("gender", "male")
            .build();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/register")
            .post(formBody)
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","content":"用户注册成功"}
    
    失败：{"status":"failure","content":"该用户已被注册"}

#### 2. 登录（login）：

- URL：http://123.207.145.251:8080/ForumSystem/login

- 参数（POST）：

|参数名|参数类型|参数说明|
|:------:|:--------:|:--------:|
|username|String|用户名(not null)|
|password|String|密码(not null)|

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
FormBody formBody = new FormBody.Builder()
            .add("username", "test")
            .add("password", "test")
            .build();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/login")
            .post(formBody)
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","content":"登录成功"}
    
    失败1：{"status":"failure","content":"登录失败"}
    
    失败2：{"status":"failure","content":"用户不存在"}

### 论坛功能：

说明：除了获取全部帖子外，其他的请求需要**先登录**才能进行请求；

#### 1. 获取全部帖子：

- URL：http://123.207.145.251:8080/ForumSystem/getallinvitations

- 请求方式：GET

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/getallinvitations")
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","invitationList":[{"author":"syuban","date":"2018-06-13 16:23:56","content":"love you, zc","iscollected":false}]}

#### 2. 获取用户所有收藏帖子：

- URL：http://123.207.145.251:8080/ForumSystem/getallfavourite

- 请求方式：GET （**说明：需先登录**）;

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/getallfavourite")
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","invitationList":[{"author":"syuban","date":"2018-06-13 16:23:56","content":"love you, zc","iscollected":true}]}
    
#### 3. 获取用户所有自己帖子：

- URL：http://123.207.145.251:8080/ForumSystem/getallown

- 请求方式：GET （**说明：需先登录**）;

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/getallown")
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","invitationList":[{"author":"syuban","date":"2018-06-13 16:23:56","content":"love you, zc","iscollected":true}]}
    
#### 4. 发布帖子：

- URL：http://123.207.145.251:8080/ForumSystem/publish

- 参数（POST）：

|参数名|参数类型|参数说明|
|:------:|:--------:|:--------:|
|content|String|发布的内容|

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
FormBody formBody = new FormBody.Builder()
            .add("content", "hello,world")
            .build();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/publish")
            .post(formBody)
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","content":"发布成功"}
    
#### 5. 删除帖子：

- URL：http://123.207.145.251:8080/ForumSystem/delete

- 参数（POST）：

|参数名|参数类型|参数说明|
|:------:|:--------:|:--------:|
|content|String|帖子内容|
|date|String|帖子时间|

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
FormBody formBody = new FormBody.Builder()
            .add("content", "hello,world")
            .add("date", "2018-06-14 10:49:45")
            .build();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/delete")
            .post(formBody)
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","content":"删除成功"}
    
#### 6. 收藏帖子：

- URL：http://123.207.145.251:8080/ForumSystem/collect

- 参数（POST）：

|参数名|参数类型|参数说明|
|:------:|:--------:|:--------:|
|owner|String|帖子作者|
|content|String|帖子内容|
|date|String|帖子时间|

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
FormBody formBody = new FormBody.Builder()
            .add("owner", "test")
            .add("content", "hello,world")
            .add("date", "2018-06-14 10:49:45")
            .build();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/collect")
            .post(formBody)
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","content":"收藏成功"}
    
#### 7. 取消收藏帖子：

- URL：http://123.207.145.251:8080/ForumSystem/discollect

- 参数（POST）：

|参数名|参数类型|参数说明|
|:------:|:--------:|:--------:|
|owner|String|帖子作者|
|content|String|帖子内容|
|date|String|帖子时间|

- 请求方法（Java）：

```
OkHttpClient client = new OkHttpClient();
FormBody formBody = new FormBody.Builder()
            .add("owner", "test")
            .add("content", "hello,world")
            .add("date", "2018-06-14 10:49:45")
            .build();
Request request = new Request.Builder()
            .url("http://123.207.145.251:8080/ForumSystem/discollect")
            .post(formBody)
            .build();
Response response = client.newCall(request).execute();
```

- 返回结果（Json）：

    成功：{"status":"success","content":"取消收藏成功"}
