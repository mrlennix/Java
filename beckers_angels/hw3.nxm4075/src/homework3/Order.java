package homework3;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Neil
 */

public class Order {

    //These are the attributes in order
    boolean status;
    int orderNumber;
    Customer c;
    Worker w;
    double totalCost;
    //ArrayList for keeping all the servings.
    ArrayList <Serving> servings;

    //This is the constructor. Constructors are called every time an object
    //is instantiated from a class.
    public Order(){

        orderNumber = 314;
        status = false;
        servings = new ArrayList<>();
        totalCost = 0;
        System.out.printf("Created %s\n", getClass().getSimpleName());
    }

    public Order (Customer cus, Worker work, ArrayList<Serving> serv,Random num){
        this.c =cus;
        this.w=work;
        this.servings=serv;
        this.orderNumber=num.nextInt(1000);
        this.status=false;
        num.setSeed(this.orderNumber);
    }

    //This function, when called, will print a report to the screen.
    public String display(){

        String result="";
        result=result+String.format("Order Number: %d\n", orderNumber);
        return result;
    }

    //This function is a setter, it updates a private attribute.
    public void setOrderNumber(int orderNumber){

        this.orderNumber = orderNumber;
    }

    public double getTotalCost(){
        for (int i = 0; i < servings.size(); i++) {
            this.totalCost += servings.get(i).getOverAllPrice();
        }
        return totalCost;
    }

    public String toString(){
        return orderNumber + "";
    }
}
