/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TestSing;

/**
 *
 * @author dorra
 */
public class testSing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        A a1= new A();
        A a2= new A();
        
        B b1 = B.getInstance();
        B b2 = B.getInstance();
        
        a1.setX(1);
        System.out.println(a1.getX());
        a2.setX(2);
        System.out.println(a1.getX());
        
        b1.setX(5);
        System.out.println(b1.getX());
        b2.setX(6);
        System.out.println(b1.getX());
        
        
        
        
    }
    
}
