package xyz.thewind.community.upload.controller

import org.apache.commons.io.FileUtils
import org.springframework.core.io.FileUrlResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import xyz.thewind.community.upload.model.UploadResponse
import xyz.thewind.music.util.Md5Encrypt
import java.io.File
import java.io.IOException


@RestController
class CommunityPictureUploadController {

    @PostMapping("/community/api/picture/upload")
    fun  uploadFile(@RequestParam("file") multipartFile: MultipartFile?) : ResponseEntity<UploadResponse> {
        val resp = UploadResponse()
        if (multipartFile == null ||multipartFile.isEmpty) {
            resp.msg = "文件错误"
            return ResponseEntity(resp, HttpStatus.OK)
        }
        if (multipartFile.bytes.size > 1024* 1024 * 5) {
            resp.msg = "图片大小超过限制"
            return ResponseEntity(resp, HttpStatus.OK)
        }
        val  folder = File(PICTURE_CACHE_PATH)
        if (!folder.exists()) {
            val succ = folder.mkdirs()
            if (!succ) {
                resp.msg = "文件目录创建失败"
                return ResponseEntity(resp, HttpStatus.OK)
            }
        }
        var finalFileName = ""
        try {
            val bArr = multipartFile.bytes
            val file = File(PICTURE_CACHE_PATH + File.separator + Md5Encrypt.convertToMd5(multipartFile.originalFilename))
            FileUtils.writeByteArrayToFile(file, bArr)
            finalFileName = Md5Encrypt.getMd5OfFile(file.absolutePath)
            val dstFile = File(PICTURE_CACHE_PATH + File.separator + finalFileName)
            FileUtils.moveFile(file, dstFile)


        } catch (_: Exception) {
            resp.msg = "上传失败"
            return ResponseEntity(resp, HttpStatus.OK)
        }
        resp.code = 0
        resp.msg = "上传成功"
        resp.list.add("https://thewind.xyz/community/api/picture/res/$finalFileName")
        return ResponseEntity(resp, HttpStatus.OK)
    }

    private fun uploadSingleFile(multipartFile: MultipartFile?): String {
        multipartFile?: return ""
        if (multipartFile.bytes.size > 1024* 1024 * 5) {
            return ""
        }
        val  folder = File(PICTURE_CACHE_PATH)
        if (!folder.exists()) {
            val succ = folder.mkdirs()
            if (!succ) return ""
        }
        var finalFileName = ""
        try {
            val bArr = multipartFile.bytes
            val file = File(PICTURE_CACHE_PATH + File.separator + Md5Encrypt.convertToMd5(multipartFile.originalFilename))
            FileUtils.writeByteArrayToFile(file, bArr)
            finalFileName = Md5Encrypt.getMd5OfFile(file.absolutePath)
            val dstFile = File(PICTURE_CACHE_PATH + File.separator + finalFileName)
            FileUtils.moveFile(file, dstFile)


        } catch (_: Exception) {
            return ""
        }
        return "https://thewind.xyz/community/api/picture/res/$finalFileName"
    }

    @PostMapping("/community/api/picture/uploads")
    fun  uploadFiles(@RequestParam("files") files: Array<MultipartFile?>?) : ResponseEntity<UploadResponse>{
        val resp = UploadResponse()
        if (files == null || files.isEmpty()) {
            resp.msg = "文件错误"
            return ResponseEntity(resp, HttpStatus.OK)
        }
        val uploadFiles = mutableListOf<String>()
        files.forEach {
            uploadFiles.add(uploadSingleFile(it))
        }
        resp.code = 0
        resp.list.addAll(uploadFiles)
        return ResponseEntity(resp, HttpStatus.OK)
    }

    @GetMapping("/api/community/picture/res/{hash}")
    fun downloadPicture(@PathVariable hash: String? = null): ResponseEntity<Resource> {
        val filePath = PICTURE_CACHE_PATH + File.separator + hash
        val resource: Resource
        try {
            resource = FileUrlResource(filePath)
        } catch (e: IOException) {
            return ResponseEntity.notFound().build()
        }
        val mediaType = MediaType.IMAGE_PNG
        return ResponseEntity.ok()
            .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.filename + "\"")
            .contentType(mediaType).body(resource)
    }



    companion object {
        private const val PICTURE_CACHE_PATH = "/home/picture/cache"
    }

}