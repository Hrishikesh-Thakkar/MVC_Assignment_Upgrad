package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //Method to fetch all comments given an image
    public List<Comment> getAllCommentsByImage(Image image){
        return commentRepository.getAllComments(image.getId());
    }

    //Method to add a Comment entity to an Image
    public void addCommentToImage(Comment newComment){
        commentRepository.addComment(newComment);
    }
}
