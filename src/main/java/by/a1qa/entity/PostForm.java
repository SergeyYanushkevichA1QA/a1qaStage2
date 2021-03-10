package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import by.a1qa.models.Post;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class PostForm extends Form {

    private static final String idAttribute = "id";
    private static final String repliesLoc = "//div[contains(@class, 'replies_list _replies_list')]";
    private static final String repliesLocFormat = repliesLoc + "[%d]";

    protected final ILabel lblMessage = getElementFactory().getLabel(By.xpath("//div[contains(@class, 'wall_post_text')]"), "Message");
    protected final ILabel lblAuthor =  getElementFactory().getLabel(By.xpath("//*[@class='author']"), "User");
    private final ILink lnkPhoto = getElementFactory().getLink(By.xpath("//a[contains(@class, 'page_post_thumb_wrap')]"), "Photo");
    private final IButton btnShowReplies =  getElementFactory().getButton(By.xpath("//a[contains(@class, 'replies_next  replies_next_main')]"), "Show comments");
    private final IButton btnLike = getElementFactory().getButton(By.className("like_button_icon"), "Like");

    public PostForm(String postId) {
        super(By.id(postId), "Post with id " + postId);
    }


    public Post getPost() {
        Post post = new Post();
        post.setMessage(lblMessage.getText());
        post.setAuthorHref(lblAuthor.getAttribute("href"));
        post.setId(getFormLabel().getAttribute(idAttribute));
        return post;
    }

    public String getImageURL() {
        String photoURL = "";
        if (lnkPhoto.state().isExist()) {
            lnkPhoto.click();
            PhotoForm photoForm = new PhotoForm();
            photoURL = photoForm.getPhotoURL();
            photoForm.clickClosePhotoBtn();
        }
        return photoURL;
    }

    public void showReplies() {
        if (btnShowReplies.state().isExist()) {
            btnShowReplies.click();
        }
    }

    public List<ReplyForm> getReplyForms() {
        List<ReplyForm> postForms = new ArrayList<>();
        List<IElement> elements = getFormLabel().findChildElements(By.xpath(repliesLoc), ElementType.LABEL);
        for (int i = 1; i <= elements.size(); i++) {
            postForms.add(new ReplyForm(By.xpath(String.format(repliesLocFormat, i)), "Post #" + i));
        }
        return postForms;
    }

    public List<Post> getReplies() {
        return getReplyForms().stream().map(ReplyForm::getReply).collect(Collectors.toList());
    }

    public void clickLikeButton() {
        btnLike.click();
    }

    public static void waitForDeletePost(String id) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> AqualityServices.getBrowser().getDriver()
                    .findElementsById(id).isEmpty());
        } catch (TimeoutException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }
}