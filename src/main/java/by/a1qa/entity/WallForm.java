package by.a1qa.entity;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import by.a1qa.models.Post;
import by.a1qa.service.ImageUtils;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WallForm extends Form {

    private static final String locator = "//div[@id='page_wall_posts']";
    private static final String postsLoc = "//div[contains(@class, '_post post page_block')]";

    public WallForm() {
        super(By.xpath(locator), "Post wall");
    }

    public List<PostForm> getPostForms() {
        List<PostForm> postForms = new ArrayList<>();
        List<IElement> elements = getFormLabel().findChildElements(By.xpath(postsLoc), ElementType.LABEL);
        for (IElement element : elements) {
            postForms.add(new PostForm(element.getAttribute("id")));
        }
        return postForms;
    }

    public List<Post> getPosts() {
        return getPostForms().stream().map(PostForm::getPost).collect(Collectors.toList());
    }

    public Post getPostWithPhoto(String postId) {
        PostForm postForm = new PostForm(postId);
        BufferedImage image = ImageUtils.getImage(postForm.getImageURL());
        Post post = postForm.getPost();
        post.setPhoto(image);
        return post;
    }

    public boolean hasPost(String id) {
        return !getFormLabel().findChildElements(By.id(id), ElementType.LABEL).isEmpty();
    }

}