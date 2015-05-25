package org.springframework.social.instagram.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Media {
	
	private String id;
	private String filter;
	private String link;
	private Caption caption;
	private InstagramProfile user;
	private Location location;
    private Date createdTime;
    private boolean userHasLiked;
    private Map<String,Image> images;
    private Map<String,Video> videos;
    private List<String> tags;
    private LikesInfo likes;
	private CommentsInfo comments;
	
	public Media(String id, String filter, String link,
			 Caption caption, InstagramProfile user, Location location,
			 Date createdTime, boolean userHasLiked, Map<String,Image> images, 
			 Map<String,Video> videos, List<String> tags, LikesInfo likes, 
			 CommentsInfo comments) {
		
		this.id = id;
		this.filter = filter;
		this.link = link;
		this.caption = caption;
		this.user = user;
		this.location = location;
        this.createdTime = createdTime;
        this.userHasLiked = userHasLiked;
        this.images = images;
        this.tags = tags;
        this.likes = likes;
        this.comments = comments;
	}
	
	public String getId() {
		return id;
	}

	public String getFilter() {
		return filter;
	}
	
	public String getLink() {
		return link;
	}

    public Caption getCaption() {
        return caption;
    }

    public InstagramProfile getUser() {
        return user;
    }

    public Location getLocation() {
        return location;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public boolean isUserHasLiked() {
        return userHasLiked;
    }
    
    public Map<String,Image> getImages() {
        return images;
    }
    
    public Map<String, Video> getVideos() {
        return videos;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    public LikesInfo getLikes() {
        return likes;
    }
    
    public CommentsInfo getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id='" + id + '\'' +
                ", filter='" + filter + '\'' +
                ", link='" + link + '\'' +
                ", caption=" + caption +
                ", user=" + user +
                ", location=" + location +
                ", createdTime=" + createdTime +
                ", userHasLiked=" + userHasLiked +
                ", images=" + images +
                ", videos=" + videos +
                ", tags=" + tags +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}
