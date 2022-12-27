package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Handles requests at {baseurl}/blogpost

@Controller
public class BlogPostController {

	@Autowired
	private BlogPostRepository blogPostRepository;
	private static List<BlogPost> posts = new ArrayList<>();
	// Handles GET requests at {baseurl}/blogpost/
	@GetMapping(value="/")
    public String index(BlogPost blogPost, Model model) {
		model.addAttribute("posts", posts);
		return "blogpost/index.html";
    }
	@GetMapping(value = "/blogposts/new")
	public String newBlog (BlogPost blogPost) {
	    return "blogpost/new.html";
	}
	private BlogPost blogPost;
	@PostMapping(value="/blogposts")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(),
				blogPost.getBlogEntry()));
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		return "blogpost/result.html";
	}
	@RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
	public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {

	    blogPostRepository.deleteById(id);
	    return "blogpost/index.html";

	}
	
 
}