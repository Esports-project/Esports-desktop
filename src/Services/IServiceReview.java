/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Entities.review;
import java.util.List;


/**
 *
 * @author dorra
 */
public interface IServiceReview <T> {
    //CRUD
    public void createReview(review R);
    public List<review> readReview();
}
