package Esprit.Views.gameScreen;


import Esprit.Entities.Games;
import Esprit.Entities.Review;
import Esprit.Services.ServiceGames;
import Esprit.Services.ServiceReview;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import java.io.FileNotFoundException;
import java.sql.SQLException;


public class ItemController {



    @FXML
    private Label title;

    @FXML
    private Label description;;

    @FXML
    private Rating rating;

    private double oldRating = 0;

    private int moy = 0;

    private Games g;

    public void setData(Games games) {
        ServiceReview review = new ServiceReview();

        review.readReviewById(games.getId()).forEach(integer -> {
            moy++;
            oldRating += integer;
        });
        title.setText(games.getNom());
        description.setText(games.getDescription());
        rating.setRating(oldRating/moy);
        g= games;

    }

    public void onRatingChanged(){

        Notifications notificationBuilder=Notifications.create()
                .title("Thanks for your review")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT);
        notificationBuilder.showConfirm();

        ServiceReview sr = new ServiceReview();
        String avis = "";
        switch ((int) rating.getRating()){
            case 2:  avis = "Bad"; break;
            case 3:  avis = "Normal"; break;
            case 4:  avis = "Good"; break;
            case 5:  avis = "Very Good"; break;
            default: String terrible = "Bad"; break;
        }
        Review r = new Review(
                g.getId(),
                (int) rating.getRating(),
                avis
        );
    sr.addReview(r);
    }

    public void func() throws SQLException, DocumentException, FileNotFoundException {

            ServiceGames tt = new ServiceGames();

            tt.exportTable(g.getId());

    }
}
