# 一、ClientManager 模块

这是由SpringBoot完成用户登录程序
数据库的初始化是自动完成
可以使用test1.http,完成用户的登录
核心逻辑是，登录后，将用户数据保存到redis中，并返回一个token

# 二、ImServer 模块

在9000端口启动了一台IM Server

操作流程如下：

## 1. 客户端john
```
nc localhost 9000
## 1号用户进入聊天室
{"code":601,"content":"6f89"}

```

## 2. 客户端tom
```
nc localhost 9000
## 1号用户进入聊天室
{"code":601,"content":"f876"}

```

## 3. 客户端mike
```
nc localhost 9000
## 1号用户进入聊天室
{"code":601,"content":"2cd3"}

```

## 4. 客户端john向tom发信息：

```
{"code":611,"from":{"id":1},"to":[{"id":2}],"content":"John Love Tom"}
```

## 5. 客户端john向mike发信息：
```
{"code":611,"from":{"id":1},"to":[{"id":3}],"content":"John Love Mike"}
```
## 6. 客户端退出：
```
{"code":602}
```

