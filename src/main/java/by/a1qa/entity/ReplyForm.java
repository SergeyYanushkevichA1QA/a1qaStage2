package by.a1qa.entity;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import by.a1qa.models.Post;
import org.openqa.selenium.By;

public class ReplyForm extends Form {


    private static final String idAttribute = "id";
    private final ITextBox txtMessage = getElementFactory().getTextBox(By.xpath("//div[contains(@class, 'wall_reply_text')]"), "Message of reply");
    private final ITextBox txtAuthor = getElementFactory().getTextBox(By.xpath("//*[@class='author']"), "User");

    public ReplyForm(By locator, String name) {
        super(locator, name);
    }

    public String getMessage() {
        return txtMessage.getText();
    }

    public Post getReply() {
        Post reply = new Post();
        reply.setMessage(getMessage());
        reply.setAuthorHref(txtAuthor.getAttribute("href"));
        reply.setId(getFormLabel().getAttribute(idAttribute));
        return reply;
    }
}