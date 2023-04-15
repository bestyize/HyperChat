package xyz.thewind.community.pixbay.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.thewind.community.pixbay.model.*
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.music.util.Base64Util
import xyz.thewind.music.util.GsonUtil

@RestController
class PictureRecommendController {

    @RequestMapping("/community/api/picture/search")
    fun searchPicture(params: String? = null): ResponseEntity<ImageSearchResponse> {
        val response = ImageSearchResponse()
        if (params.isNullOrBlank()) {
            response.msg = "参数校验错误"
            return ResponseEntity(response, HttpStatus.OK)
        }

        try {

            val resp = HttpUtil.get("https://pixabay.com/api/?${Base64Util.decode(params)}&key=35359169-d0387b5efe4184e8a51381124")
            val bayResp = GsonUtil.fromJson(resp, PixbayImageSearchResponse::class.java)
            response.code = 0
            response.msg = "搜索到" + bayResp.total + "张图片"
            val list: MutableList<ImageSearchData> = mutableListOf()
            bayResp.list.forEach {
                list.add(ImageSearchData().apply {
                    this.id = it.id
                    this.type = it.type
                    this.tags = it.tags
                    this.pageUrl = it.pageUrl
                    this.imageUrl = it.imageUrl
                    this.width = it.width
                    this.height = it.height
                    this.views = it.views
                    this.downloads = it.downloads
                    this.collections = it.collections
                })
            }
            response.data = list

        } catch (_:Exception) {}
        return ResponseEntity(response, HttpStatus.OK)
    }

    @RequestMapping("/community/api/picture/recommend/tabs")
    fun getRecommendTabs(): ResponseEntity<PictureRecommendTabsResponse> {
        return ResponseEntity(loadRecommendTabs(), HttpStatus.OK)
    }

    fun loadRecommendTabs(): PictureRecommendTabsResponse {
        val list: MutableList<PictureRecommendTab> = mutableListOf()
        list.add(PictureRecommendTab().apply {
            this.name = "推荐"
            this.query = PixbayImageQuery().apply {
                this.page = 1
                this.lang = "zh"
                this.num = 20
                this.q = "精彩"
            }
        })
        list.add(PictureRecommendTab().apply {
            this.name = "壁纸"
            this.query = PixbayImageQuery().apply {
                this.page = 1
                this.lang = "zh"
                this.num = 20
                this.q = "壁纸"
            }
        })
        list.add(PictureRecommendTab().apply {
            this.name = "城市"
            this.query = PixbayImageQuery().apply {
                this.page = 1
                this.lang = "zh"
                this.num = 20
                this.q = "城市"
            }
        })
        list.add(PictureRecommendTab().apply {
            this.name = "美女"
            this.query = PixbayImageQuery().apply {
                this.page = 1
                this.lang = "zh"
                this.num = 20
                this.q = "美女"
            }
        })
        list.add(PictureRecommendTab().apply {
            this.name = "风景"
            this.query = PixbayImageQuery().apply {
                this.page = 1
                this.lang = "zh"
                this.num = 20
                this.q = "风景"
            }
        })
        list.add(PictureRecommendTab().apply {
            this.name = "卡通"
            this.query = PixbayImageQuery().apply {
                this.page = 1
                this.lang = "zh"
                this.num = 20
                this.q = "精彩"
            }
        })
        list.add(PictureRecommendTab().apply {
            this.name = "插画"
            this.query = PixbayImageQuery().apply {
                this.page = 1
                this.lang = "zh"
                this.num = 20
                this.q = "插画"
            }
        })

        return PictureRecommendTabsResponse().apply {
            code = 0
            data = list
        }
    }
}