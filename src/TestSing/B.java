/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestSing;

/**
 *
 * @author dorra
 */
public class B {
    int X;
    public static B instance;
    
 private B (){}
 
 public static B getInstance(){
     if (instance == null)
         instance= new B();
     return instance;
     }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }
 
}
