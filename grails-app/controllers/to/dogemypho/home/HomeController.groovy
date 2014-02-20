package to.dogemypho.home

import grails.transaction.Transactional

import org.apache.commons.io.FileUtils

import to.dogemypho.pic.DogePhoto

@Transactional
class HomeController {
	static final int BUFF_SIZE = 100000
	static final byte[] buffer = new byte[BUFF_SIZE]
	static final ALLOWED_CONTENT_TYPES = ['image/gif', 'image/jpeg', 'image/x-png']
	
	def imageService
	def dogeImageIndirectService
	
	def index() {
		def result = DogePhoto.executeQuery("from DogePhoto dp order by dp.id desc", [:], [max: 1])
		[dogePhotoPath: result?.originalThumbnailPath]
	}

	def uploadPhoto() {
		def contentType	= params?.dogePhoto?.contentType
		
		if (!ALLOWED_CONTENT_TYPES.findAll {it.indexOf(contentType) != -1}.empty) {
			flash.error = "Oh Noes! Only ${ALLOWED_CONTENT_TYPES} content types allowed!"
			render (view: 'index')
			return
		}
		
		File originalPhotoFile = dogeImageIndirectService.storeImage(params.dogePhoto)
		
		byte[] thumbnailedBytes = imageService.createThumbnail(new FileInputStream(file), 500)
		
		//store thumbnailed image
		File thumbnailedPhotoFile = dogeImageIndirectService.storeImageFromByteArray(thumbnailedBytes)
		
		DogePhoto dogePhoto = new DogePhoto()
		dogePhoto.originalPath = file.absolutePath
		dogePhoto.originalThumbnailPath = thumbnailedPhotoFile.absolutePath
		dogePhoto.save()
		
		render(view: 'index', model: [dogePhotoPath: dogePhoto.originalThumbnailPath])
	}
}
