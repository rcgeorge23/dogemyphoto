package to.dogemypho.pic

import org.apache.commons.io.FileUtils
import org.springframework.web.multipart.MultipartFile

class DogeImageIndirectService {

    static transactional = false

    def grailsApplication
    def grailsLinkGenerator

    String fullPath(String category = null) {

        String returnPath = grailsApplication.config.imageindirect.basePath
        String categoryUrl = category ? grailsApplication.config.imageindirect.category."${category}" : null

        if (categoryUrl) {
            if (categoryUrl.startsWith("/")) {
                returnPath = categoryUrl
            } else {
                returnPath += "/${categoryUrl}"
            }
        }
		
		File folder = new File(returnPath)
		
		if (!folder.exists()) {
			folder.mkdirs()
		}
		
        return returnPath
    }

    File storeImage(MultipartFile multipartFile, String desiredName = null, String category = null) {
        if (!multipartFile) {
            return null
        }

        def storagePath = fullPath(category)
        def physicalFileName = desiredName ?: multipartFile.originalFilename
        File file = new File(storagePath, physicalFileName)
        multipartFile.transferTo(file)
        return file
    }
	
	File storeImageFromByteArray(byte[] bytes, String desiredName = null, String category = null) {
		def storagePath = fullPath(category)
		def physicalFileName = System.currentTimeMillis().toString()
		File file = new File(storagePath, physicalFileName)
		FileUtils.writeByteArrayToFile(file, bytes)
		return file
	}

    String imageLink( String imageName, String category) {
        grailsLinkGenerator.link(controller: 'imageIndirect' , params: [imageName: imageName, category:category] )
    }

    String lastResortImage() {
        grailsApplication.config.imageindirect.nophoto
    }
}
