# WechatWeb
仿微信实现的服务器端

[查看对应的客户端项目](https://github.com/imu-hupeng/WechatApp)

# WechatWeb的API说明(v1)
## *技术概要：
客户端推荐采用
XUtils3框架，Gson写
<br>
v1版本HTTP服务器地址为：http://[IP地址尚未确定]:8080/WeatherWeb/v1 ,建议写在配置文件,方便修改


## 项目的相关说明：
#### 作者：hupeng(hupeng@imudges.com)
#### 完成时间：2017-08-10 -- 2017-08-17
#### 本项目由[Nutz框架](http://nutzam.com/)构建

## HTTP协议接口
### 普通登录
#### 地址：/login/username/[用户的的用户名]/password/[用户的密码]
#### 成功返回结果样例：
```json
{
  "rst": 0,
  "msg": "ok",
  "data": {
    "user": {
      "id": 1,
      "username": "admin",
      "ak": "sRQ7Ij715cD8yMKNsMflZp0VnLBI08gfGk5lUVgsQCRLscCCoyG5RC1uKvtOICxs",
      "sex": false,
      "userPhoto": null
    }
  }
}
```
#### 失败返回结果样例：
```json
{
  "rst": -1,
  "msg": "用户名或者密码错误",
  "data": null
}
```
#### 返回码含义:
| 返回码        | 含义   |
| :----: | :----:  |
|  0     | 登录成功，返回用户的信息以及鉴权参数accessKey，请保存此参数   |
| -1     | 登录失败   |

<br>

### 获取联系人列表
#### 地址：get_relationship_list
#### 参数：ak=[由登录成功返回]
#### 成功返回结果样例：
```json
{
  "rst": 0,
  "msg": "ok",
  "data": [
    {
      "id": 2,
      "username": "admin1",
      "ak": null,
      "sex": false,
      "userPhoto": null
    },
    {
      "id": 3,
      "username": "admin2",
      "ak": null,
      "sex": false,
      "userPhoto": null
    },
    {
      "id": 4,
      "username": "admin3",
      "ak": null,
      "sex": false,
      "userPhoto": null
    }
  ]
}
```
#### 失败返回结果样例：
```json
{
  "rst": -100,
  "msg": "用户登录状态失效，请重新登录",
  "data": null
}
```
#### 返回码含义:
| 返回码        | 含义   |
| :----: | :----:  |
|  0     | 联系人列表获取成功   |
| -100     | 登录状态失效（注意在任何时候返回-100返回值都要求用户进行重新登录操作，此操作在客户端要有所体现，以下出现的-100返回码都是此含义）   |

<br>

### 获取联系人列表的大小（便于确定是否需要更新客户端的本地的联系人列表）
#### 地址：get_relationship_list_count
#### 参数：ak=[由登录成功返回]
#### 成功返回结果样例：
```json
{
  "rst": 0,
  "msg": "ok",
  "data": 3
}
```
#### 失败返回结果样例：
```json
{
  "rst": -100,
  "msg": "用户登录状态失效，请重新登录",
  "data": null
}
```
#### 返回码含义:
| 返回码        | 含义   |
| :----: | :----:  |
|  0     | 联系人列表获取成功   |
| -100     | 同上   |

<br>

### 确定用户的登录状态
#### 地址：check_user_login_status
#### 参数：ak=[由登录成功返回]
#### 成功返回结果样例：
```json
{
  "rst": 0,
  "msg": "ok",
  "data": {
    "id": 1,
    "username": "admin",
    "ak": "1MCMHqjAMh6jti18aL3O9u3OQMpsS7i3H6CF2W5EPeYT6L4iSmRWTnfGMwpkJtlV",
    "sex": false,
    "userPhoto": null
  }
}
```
#### 失败返回结果样例：
```json
{
  "rst": -100,
  "msg": "用户登录状态失效，请重新登录",
  "data": null
}
```
#### 返回码含义:
| 返回码        | 含义   |
| :----: | :----:  |
|  0     | 登录状态有效   |
| -100     | 同上  |

<br><br><br>

v1版本WS服务器地址为：ws://[IP地址尚未确定]:8080/WeatherWeb/v1 

## WebSocket协议接口
### 实现聊天功能，实时进行聊天消息的上传以及下发
#### 地址：chat
#### 参数：交互采用一个json字符串，形如
发送心跳包：

```json
{"operate":0,"from":1,"to":0}
```

上线:

```json
{
    "operate":1,
    "from":1,
    "to":0
}
```

发送聊天消息：

```json
{
    "sendTime":"2017-08-17 19:25:05",
    "message":"641d17127f39a146",
    "operate":2,
    "from":1,
    "to":2
}
```

#### 相关变量的说明：

| 变量        | 含义   |
| :----: | :----:  |
|  from    | 发送者的USER_ID   |
|  to      | 接收者的USER_ID   |
|  sendTime | 发送时间，尽在发送聊天消息的时候使用   |
|  message  | 发送的消息内容，样例中的消息内容已经经过了加密   |
|  operate  | 操作类型：0,心跳;1,上线;2:发送文本消息   |



