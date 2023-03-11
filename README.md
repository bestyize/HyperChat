# HyperChat

## 1、音乐搜索接口


## 2、磁力搜索接口

### 2.1 磁力搜索接口

1、 搜索接口API

| 项目      | 取值                                     | 备注      |
|---------|----------------------------------------|---------|
| api     | https://thewind.xyz/torrent/api/search | 接口地址    |
| src     | 0 、1 、2、3、4                            | 搜索源     |
| keyword | 字符串                                    | 搜索关键词   |
| page    | 1、2、3...                               | 搜索的当前页数 |

接口示例

```html
https://thewind.xyz/torrent/api/search?src=0&keyword=流浪地球&page=1
```

返回值

```java
TorrentSearchResponse
```

2、磁力接口API

| 项目   | 取值                                         | 备注                |
|------|--------------------------------------------|-------------------|
| api  | https://thewind.xyz/torrent/api/magnetlink | 接口地址              |
| src  | 0 、1 、2、3、4                                | 搜索源               |
| link | 字符串                                        | 详情页网址,需要URLEncode |

接口示例

```html
https://thewind.xyz/torrent/api/magnetlink?src=0&link=xxx
```

返回值

磁力链接

3、磁力配置API

| 项目  | 取值                                            | 备注   |
|-----|-----------------------------------------------|------|
| api | https://thewind.xyz/torrent/api/supportsource | 接口地址 |

返回值

```java
TorrentConfigResponse
```
