package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String addComment(@PathVariable("id") Integer id, @PathVariable("title") String title, @RequestParam("comment") String comment, HttpSession httpSession){
        Comment newComment = new Comment();
        Date input = new Date();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();
        User user = (User) httpSession.getAttribute("loggeduser");
        Image image = imageService.getImage(id);
        newComment.setImage(image);
        newComment.setUser(user);
        newComment.setCreatedDate(date);
        newComment.setText(comment);
        commentService.addCommentToImage(newComment);
        return "redirect:/images/" + id + '/' + title;
    }
}
