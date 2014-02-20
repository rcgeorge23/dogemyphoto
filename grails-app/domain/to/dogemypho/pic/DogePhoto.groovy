package to.dogemypho.pic

class DogePhoto {
	Integer rating
	String originalPath
	String originalThumbnailPath
	String dogeThumbnailPath
	
	static constraints = {
		rating nullable: true
		originalPath nullable: false
		originalThumbnailPath nullable: false
		dogeThumbnailPath nullable: true
	}
	
	static mapping = {
	}
}
