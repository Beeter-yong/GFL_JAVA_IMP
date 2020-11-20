# GFL_JAVA_IMP

以 GFL 为思想，使用 JAVA RMI 简单模拟

## 基本项目分为三个部分

初始化均为 Maven 项目

### Client

- 功能
  - write
    - 获取一个文件、文件名
    - 读取文件大小
    - 发送文件名和大小给 Master
    - 根据 Maste 传回的 ip、port 将相应的字节存放在各个 Chunk 并发送给 ChunkServer
  - read
  - append
  - exit
  - delete

### Master

- 具有两个端口
  - 6666：监听 Client
    - 功能：
      - write
        - 获取 Client 的文件名和大小
        - 根据大小选取合适的 Chunk 和 ChunkServer 给 Client（需要将备份 ChunkServer 也传回）
  - 8888：监听 Chunk

### ChunkServer

- 开放端口 7777 为 Client 服务
  - 功能
    - Write
      - 根据 Client 传过来的 Chunk 进行存储
      - 响应 Client 是否成功
      - 保存 Chunk 名字与位置的映射
