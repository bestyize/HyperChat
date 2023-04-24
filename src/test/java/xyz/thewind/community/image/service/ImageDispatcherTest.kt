package xyz.thewind.community.image.service

import org.junit.jupiter.api.Test
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.music.util.GsonUtil


class ImageDispatcherTest {

    @Test
    fun testPexels() {
        val result = ImageDispatcher.search("recommend", 1, 0, ImageSrc.PEXELS.src)
        print(GsonUtil.toJson(result))

    }

    @Test
    fun testKonaChan() {
        val result = ImageDispatcher.search("", 1, 0, ImageSrc.KONACHAN.src)
        print(GsonUtil.toJson(result))

    }

    @Test
    fun testWallHaven() {
        val result = ImageDispatcher.search("", 1, 0, ImageSrc.WALLHAVEN.src)
        print(GsonUtil.toJson(result))
    }

    @Test
    fun testHubble() {
        val result = ImageDispatcher.search("", 1, 0, ImageSrc.HUBBLE.src)
        print(GsonUtil.toJson(result))
    }

    @Test
    fun testUnSplash() {
        val result = ImageDispatcher.search("/napi/topics/nature/photos?xp=search-quality-boosting%3Acontrol", 1, 12, ImageSrc.UNSPLASH.src)
        print(GsonUtil.toJson(result))
    }


}