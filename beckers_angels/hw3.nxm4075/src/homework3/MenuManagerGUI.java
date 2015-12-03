package homework3;

import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.ArrayList;
import java.util.Random;
import javafx.stage.FileChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javafx.scene.image.Image;
import java.util.Arrays;

public class MenuManagerGUI extends Application {

    private Shop shopper;
    //ArrayList<Customer> customer;
    //ArrayList <Worker> worker;
    //ArrayList <IceCreamMain> IceCreamList;
    //added
    private static Stage stage;
    private static MenuBar menubar;
    Random rand = new Random(1000);
    protected int width = 500;
    protected int height = 700;


    public MenuManagerGUI() {
        //customer = new ArrayList<>();
        //worker = new ArrayList<>();
        //IceCreamList = new ArrayList<>();
        shopper = new Shop();
    }

    public void setShopcontroller(Shop shopcontroller){
        this.shopper = shopcontroller;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage=primaryStage;
        stage.setScene(primaryStage());
        stage.show();
    }

    public Scene primaryStage() {
        BorderPane layout;

        stage.setTitle("Honey Badger Ice Cream Parlor");
        
        //File menu
        Menu fileMenu = new Menu("File");
        MenuItem fileIceCream = new MenuItem("Ice Cream");
        fileIceCream.setOnAction((ActionEvent event) -> {
            iceCreamFileChooser(stage);
        });
        MenuItem fileWorkers = new MenuItem("Workers");
        fileWorkers.setOnAction((ActionEvent event) -> {
            workerFileChooser(stage);
        });
        MenuItem fileCustomers = new MenuItem("Customers");
        fileCustomers.setOnAction((ActionEvent event) -> {
            customerFileChooser(stage);
        });
        Menu fileSave = new Menu("Save");
        MenuItem saveAll = new MenuItem("Save All");
        saveAll.setOnAction(e -> {
            shopper.saveAllFiles();
        });
        MenuItem saveIceCream = new MenuItem("Save Ice Cream");
        saveIceCream.setOnAction(e -> {
            shopper.ic.saveIceCream(shopper.iceCreamList);
        });
        MenuItem saveWorkers = new MenuItem("Save Workers");
        saveWorkers.setOnAction(e -> {
            shopper.wor.saveWorker(shopper.workerList);
        });
        MenuItem saveCustomers = new MenuItem("Save Customers");
        saveCustomers.setOnAction(e -> {
            shopper.cus.saveCustomer(shopper.customerList);
        });
        fileSave.getItems().addAll(saveAll, saveIceCream, saveWorkers, saveCustomers);
        MenuItem fileExit = new MenuItem("Exit");
        fileExit.setOnAction((ActionEvent event) -> {
            stage.close();
        });
        fileMenu.getItems().addAll(fileIceCream, fileWorkers, fileCustomers, fileSave, fileExit);
        
        //Create Menu
        Menu createMenu = new Menu("Create");
        MenuItem createIceCream = new MenuItem("Create Ice Cream");
        createIceCream.setOnAction(e -> {
            stage.setScene(new Scene(createIceCream(), width, height));
        });
        MenuItem createWorker = new MenuItem("Create Worker");
        createWorker.setOnAction(e -> {
            stage.setScene(new Scene(createWorker(), width, height));
        });
        MenuItem createCustomer = new MenuItem("Create Customer");
        createCustomer.setOnAction(e -> {
            stage.setScene(new Scene(createCustomer(), width, height));
        });
        createMenu.getItems().addAll(createIceCream, createWorker, createCustomer);

        //Update Menu
        Menu updateMenu = new Menu("Update");
        MenuItem updateIceCream = new MenuItem("Update Ice Cream");
        updateIceCream.setOnAction(e -> {
            stage.setScene(new Scene(build_ice(), width, height));
        });
        MenuItem updateWorker = new MenuItem("Update Worker");
        updateWorker.setOnAction(e -> {
            stage.setScene(new Scene(build_worker(), width, height));
        });
        MenuItem updateCustomer = new MenuItem("Update Customer");
        updateCustomer.setOnAction(e -> {
            stage.setScene(new Scene(build_customer(), width, height));
        });
        updateMenu.getItems().addAll(updateIceCream, updateWorker, updateCustomer);

        //Task Menu
        Menu tasksMenu = new Menu("Tasks");
        MenuItem tasksPayOrder = new MenuItem("Pay Order");
        tasksPayOrder.setOnAction(e -> {
            stage.setScene(new Scene(pay_order(), width, height));
        });
        MenuItem tasksPlaceOrder = new MenuItem("Place Order");
        tasksPlaceOrder.setOnAction(e -> {
            stage.setScene(new Scene(place_order(), width, height));
        });
        MenuItem tasksActiveCashier = new MenuItem("Active Cashier");
        tasksActiveCashier.setOnAction(e -> {
            stage.setScene(new Scene(active_cashier(), width, height));
        });
        MenuItem tasksActiveStocker = new MenuItem("Active Stocker");
        tasksActiveStocker.setOnAction(e -> {
            stage.setScene(new Scene(active_stocker(), width, height));
        });
        MenuItem tasksCashierOnBreak = new MenuItem("Cashier on Break");
        tasksCashierOnBreak.setOnAction(e -> {
            stage.setScene(new Scene(set_cashier_break(), width, height));
        });
        MenuItem tasksStockerOnBreak = new MenuItem("Stocker on Break");
        tasksStockerOnBreak.setOnAction(e -> {
            stage.setScene(new Scene(set_stocker_break(), width, height));
        });
        tasksMenu.getItems().addAll(tasksPayOrder, tasksPlaceOrder,
                tasksActiveCashier, tasksActiveStocker, tasksCashierOnBreak,
                tasksStockerOnBreak);
        
        //Charts Menu
        Menu chartsMenu = new Menu("Charts");
        MenuItem chartsHappinessPie = new MenuItem("Happiness Pie Chart");
        chartsHappinessPie.setOnAction((ActionEvent event) -> {

            int numberofcus=shopper.customerList.size();
            int x[]= new int[numberofcus];
            for(int i=0;i<numberofcus;i++)

            {x[i] = shopper.customerList.get(i).levelOfHappiness;

            }
            DefaultPieDataset pieDataset=new DefaultPieDataset();

            for( int i=0;i<numberofcus;i++)

            {  pieDataset.setValue(shopper.customerList.get(i).name,new Integer(x[i]));}

            JFreeChart chart= ChartFactory.createPieChart3D("Happiness Pie Chart", pieDataset, true, true, true);
            PiePlot3D P=(PiePlot3D)chart.getPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Happiness Pie Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,450);
        });
        MenuItem chartsMoneyPie = new MenuItem("Money Pie Chart");

        chartsMoneyPie.setOnAction((ActionEvent event) -> {

            int numberofwork=shopper.workerList.size();
            double x[]= new double[numberofwork];
            for(int i=0;i<numberofwork;i++)

            {x[i] = shopper.workerList.get(i).moneyTaken;

            }
            DefaultPieDataset pieDataset=new DefaultPieDataset();

            for( int i=0;i<numberofwork;i++)

            {  pieDataset.setValue(shopper.workerList.get(i).workerName,new Double(x[i]));}

            JFreeChart chart= ChartFactory.createPieChart3D("Money Pie Chart", pieDataset, true, true, true);
            PiePlot3D P=(PiePlot3D)chart.getPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Money Pie Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,450);
        });

        MenuItem chartsHappinessBar = new MenuItem("Happiness Bar Chart");
        chartsHappinessBar.setOnAction((ActionEvent event) -> {

            int numberofcus=shopper.customerList.size();
            int x[]= new int[numberofcus];
            String c[]= new String[numberofcus];

            for(int i=0;i<numberofcus;i++)

            {x[i] = shopper.customerList.get(i).levelOfHappiness;
                c[i]=shopper.customerList.get(i).name;

            }
            int temp;
            String temp1;
            for(int i=0;i<numberofcus;i++)
            {for(int j=1;j<numberofcus-i;j++)
            {if(x[j-1]>x[j])
            {temp=x[j-1];
                temp1=c[j-1];
                x[j-1]=x[j];
                c[j-1]=c[j];
                c[j]=temp1;
                x[j]=temp;
            }}}

            DefaultCategoryDataset dataset=new DefaultCategoryDataset();

            for( int i=0;i<numberofcus;i++)

            {  dataset.setValue(new Integer(x[i]),"Happiness",c[i]);}

            JFreeChart chart= ChartFactory.createBarChart("Happiness Bar Chart", "Customers","Happiness", dataset, PlotOrientation.VERTICAL, false,true,false);
            CategoryPlot P=chart.getCategoryPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Happiness Bar Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,450);
        });


        MenuItem chartsMoneyBar = new MenuItem("Money Bar Chart");
        chartsMoneyBar.setOnAction((ActionEvent event) -> {

            int numberofwork=shopper.workerList.size();
            String c[]=new String[numberofwork];
            double x[]= new double[numberofwork];
            for(int i=0;i<numberofwork;i++)

            {x[i] = shopper.workerList.get(i).moneyTaken;
                c[i]=shopper.workerList.get(i).workerName;


            }
            double temp;
            String temp1;
            for(int i=0;i<numberofwork;i++)
            {for(int j=1;j<numberofwork-i;j++)
            {if(x[j-1]>x[j])
            {temp=x[j-1];
                temp1=c[j-1];
                x[j-1]=x[j];
                c[j-1]=c[j];
                c[j]=temp1;
                x[j]=temp;
            }}}
            DefaultCategoryDataset dataset=new DefaultCategoryDataset();

            for( int i=0;i<numberofwork;i++)

            {  dataset.setValue(new Double(x[i]),"Money",c[i]);}

            JFreeChart chart= ChartFactory.createBarChart("Money Bar Chart", "Worker","Money", dataset, PlotOrientation.VERTICAL, false,true,false);
            CategoryPlot P=chart.getCategoryPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Money Bar Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,450);
        });



        chartsMenu.getItems().addAll(chartsHappinessPie, chartsMoneyPie,
                chartsHappinessBar, chartsMoneyBar);
        
        //About Menu
        Menu aboutMenu = new Menu("About");
        MenuItem showAbout = new MenuItem("Show About");
        showAbout.setOnAction(e -> {
            stage.setScene(new Scene(aboutPage(), width, height));
        });

        aboutMenu.getItems().add(showAbout);
        //Create the Menu bar along the top
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, createMenu, updateMenu, tasksMenu,
                chartsMenu, aboutMenu);

        //Create the Menu bar along the top
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, createMenu, updateMenu, tasksMenu,
                chartsMenu, aboutMenu);

        Image img = new Image(Main.class.getResourceAsStream("beckersangel.jpg"));
        ImageView view = new ImageView(img);


        this.menubar=menuBar;
        layout = new BorderPane();
        layout.setCenter(view);
        layout.setTop(menuBar);
        Scene scene = new Scene(layout, width, height);
        scene.getStylesheets().add(getClass().getResource("viper.css").toExternalForm());
        //stage.setScene(scene);
        //stage.show();
        return scene;
    }

    public void iceCreamFileChooser(Stage stage) {
        FileChooser icFile = new FileChooser();
        icFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = icFile.showOpenDialog(stage);

        if (file != null) {
            shopper.createIceCreamArrayList(file);
        }
    }

    public void workerFileChooser(Stage stage) {
        FileChooser wFile = new FileChooser();
        wFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = wFile.showOpenDialog(stage);
        if (file != null) {
            shopper.createWorkerArrayList(file);
        }
    }

    public void customerFileChooser(Stage stage) {
        FileChooser cFile = new FileChooser();
        cFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = cFile.showOpenDialog(stage);

        if (file != null) {
            shopper.createCustomerArrayList(file);
        }
    }

    public BorderPane createIceCream() {
        BorderPane layout = new BorderPane();
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        //name
        Label nameLabel = new Label("Name:");
        TextField nameText = new TextField();
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameText, 1, 0);

        //price
        Label priceLabel = new Label("Price:");
        TextField priceText = new TextField();
        GridPane.setConstraints(priceLabel, 0, 1);
        GridPane.setConstraints(priceText, 1, 1);

        //flavor
        Label flavorLabel = new Label("Flavor:");
        TextField flavorText = new TextField();
        GridPane.setConstraints(flavorLabel, 0, 2);
        GridPane.setConstraints(flavorText, 1, 2);

        //description
        Label descriptionLabel = new Label("Description:");
        TextField descriptionText = new TextField();
        GridPane.setConstraints(descriptionLabel, 0, 3);
        GridPane.setConstraints(descriptionText, 1, 3);

        //scoops
        Label scoopLabel = new Label("Number of Scoops:");
        TextField scoopText = new TextField("80");
        GridPane.setConstraints(scoopLabel, 0, 4);
        GridPane.setConstraints(scoopText, 1, 4);

        //Submit
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 5);

        submit.setOnAction(e -> {
            shopper.addIceCream(nameText.getText(), priceText.getText(), flavorText.getText(),
                    descriptionText.getText(), scoopText.getText());
            stage.setScene(primaryStage());
        });

        pane.getChildren().addAll(nameLabel, nameText, priceLabel, priceText, flavorLabel, flavorText,
                descriptionLabel, descriptionText, scoopLabel, scoopText, submit);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    public BorderPane createWorker() {
        BorderPane layout = new BorderPane();
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        //worker type
        Label typeLabel = new Label("Worker Type:");
        ChoiceBox<String> typeChoice = new ChoiceBox<>();
        typeChoice.getItems().addAll("Worker", "Stocker", "Cashier");
        typeChoice.setValue("Worker");
        GridPane.setConstraints(typeLabel, 0, 0);
        GridPane.setConstraints(typeChoice, 1, 0);

        //name
        Label nameLabel = new Label("Name:");
        TextField nameText = new TextField();
        GridPane.setConstraints(nameLabel, 0, 1);
        GridPane.setConstraints(nameText, 1, 1);

        //Id number
        Label idLabel = new Label("ID Number:");
        TextField idText = new TextField();
        GridPane.setConstraints(idLabel, 0, 2);
        GridPane.setConstraints(idText, 1, 2);

        //submit
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 3);

        submit.setOnAction(e -> {
            Worker wor;
            switch (typeChoice.getValue()) {
                case "Worker":
                    wor = new Worker();
                    break;
                case "Stocker":
                    wor = new Stocker();
                    break;
                case "Cashier":
                    wor = new Cashier();
                    break;
                default:
                    wor = new Worker();
                    break;
            }

            wor.workerName = nameText.getText();
            wor.idNumber = Long.parseLong(idText.getText());
            shopper.workerList.add(wor);
            stage.setScene(primaryStage());
        });

        pane.getChildren().addAll(typeLabel, typeChoice, nameLabel, nameText, idLabel, idText, submit);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    public BorderPane createCustomer() {
        BorderPane layout = new BorderPane();
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        //name
        Label nameLabel = new Label("Name:");
        TextField nameText = new TextField();
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameText, 1, 0);

        //id number
        Label idLabel = new Label("ID Number:");
        TextField idText = new TextField();
        GridPane.setConstraints(idLabel, 0, 1);
        GridPane.setConstraints(idText, 1, 1);

        //level of happiness
        Label happinessLabel = new Label("Happiness Level:");
        TextField happinessText = new TextField();
        GridPane.setConstraints(happinessLabel, 0, 2);
        GridPane.setConstraints(happinessText, 1, 2);

        //wallet
        Label walletLabel = new Label("Cash in Wallet:");
        TextField walletText = new TextField();
        GridPane.setConstraints(walletLabel, 0, 3);
        GridPane.setConstraints(walletText, 1, 3);

        //submit
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 4);

        submit.setOnAction(e -> {
            shopper.addCustomer(nameText.getText(), idText.getText(), happinessText.getText(), walletText.getText());
            stage.setScene(primaryStage());
        });
        pane.getChildren().addAll(nameLabel, nameText, idLabel, idText, happinessLabel, happinessText, walletLabel,
                walletText, submit);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    public Transaction createTransaction(Order order, Customer cus){
        Transaction tran = new Transaction();
        Stage tranStage = new Stage();
        tranStage.setTitle("Transaction");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.getColumnConstraints().addAll(new ColumnConstraints(), new ColumnConstraints(),
                new ColumnConstraints(250));

        Label totalLabel = new Label("Total:");
        Label total = new Label(String.format("$%.2f", order.getTotalCost()));
        GridPane.setConstraints(totalLabel, 0, 0);
        GridPane.setConstraints(total, 1, 0);

        Label penniesLabel = new Label("Pennies:");
        Slider penniesSlider = new Slider();
        Text pennyTotal = new Text(String.format("%d" ,(int)penniesSlider.getValue()));
        penniesSlider.setMin(0);
        penniesSlider.setMax(cus.t.pennies);
        penniesSlider.setShowTickLabels(true);
        penniesSlider.setShowTickMarks(true);
        penniesSlider.setBlockIncrement(1);
        GridPane.setConstraints(penniesLabel, 0, 1);
        GridPane.setConstraints(penniesSlider, 2, 1);
        GridPane.setConstraints(pennyTotal, 3, 1);

        penniesSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                pennyTotal.setText(String.format("%d" ,(int)penniesSlider.getValue()));
            }
        });

        Label nickelLabel = new Label("Nickels:");
        Slider nickelSlider = new Slider();
        Text nickelTotal = new Text(String.format("%d" ,(int)nickelSlider.getValue()));
        nickelSlider.setMin(0);
        nickelSlider.setMax(cus.t.nickels);
        nickelSlider.setShowTickLabels(true);
        nickelSlider.setShowTickMarks(true);
        nickelSlider.setBlockIncrement(1);
        GridPane.setConstraints(nickelLabel, 0, 2);
        GridPane.setConstraints(nickelSlider, 2, 2);
        GridPane.setConstraints(nickelTotal, 3, 2);

        nickelSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                nickelTotal.setText(String.format("%d" ,(int)nickelSlider.getValue()));
            }
        });

        Label dimeLabel = new Label("Dimes:");
        Slider dimeSlider = new Slider();
        Text dimeTotal = new Text(String.format("%d" ,(int)dimeSlider.getValue()));
        dimeSlider.setMin(0);
        dimeSlider.setMax(cus.t.dimes);
        dimeSlider.setShowTickLabels(true);
        dimeSlider.setShowTickMarks(true);
        dimeSlider.setBlockIncrement(1);
        GridPane.setConstraints(dimeLabel, 0, 3);
        GridPane.setConstraints(dimeSlider, 2, 3);
        GridPane.setConstraints(dimeTotal, 3, 3);

        dimeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                dimeTotal.setText(String.format("%d" ,(int)dimeSlider.getValue()));
            }
        });

        Label quarterLabel = new Label("Quarters:");
        Slider quarterSlider = new Slider();
        Text quarterTotal = new Text(String.format("%d" ,(int)quarterSlider.getValue()));
        quarterSlider.setMin(0);
        quarterSlider.setMax(cus.t.quarters);
        quarterSlider.setShowTickLabels(true);
        quarterSlider.setShowTickMarks(true);
        quarterSlider.setBlockIncrement(1);
        GridPane.setConstraints(quarterLabel, 0, 4);
        GridPane.setConstraints(quarterSlider, 2, 4);
        GridPane.setConstraints(quarterTotal, 3, 4);

        quarterSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                quarterTotal.setText(String.format("%d" ,(int)quarterSlider.getValue()));
            }
        });

        Label oneLabel = new Label("Ones:");
        Slider oneSlider = new Slider();
        Text oneTotal = new Text(String.format("%d" ,(int)oneSlider.getValue()));
        oneSlider.setMin(0);
        oneSlider.setMax(cus.t.ones);
        oneSlider.setShowTickLabels(true);
        oneSlider.setShowTickMarks(true);
        oneSlider.setBlockIncrement(1);
        GridPane.setConstraints(oneLabel, 0, 5);
        GridPane.setConstraints(oneSlider, 2, 5);
        GridPane.setConstraints(oneTotal, 3, 5);

        oneSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                oneTotal.setText(String.format("%d" ,(int)oneSlider.getValue()));
            }
        });

        Label fiveLabel = new Label("Fives:");
        Slider fiveSlider = new Slider();
        Text fiveTotal = new Text(String.format("%d" ,(int)fiveSlider.getValue()));
        fiveSlider.setMin(0);
        fiveSlider.setMax(cus.t.fives);
        fiveSlider.setShowTickLabels(true);
        penniesSlider.setShowTickMarks(true);
        penniesSlider.setBlockIncrement(1);
        GridPane.setConstraints(fiveLabel, 0, 6);
        GridPane.setConstraints(fiveSlider, 2, 6);
        GridPane.setConstraints(fiveTotal, 3, 6);

        fiveSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                fiveTotal.setText(String.format("%d" ,(int)fiveSlider.getValue()));
            }
        });

        Label tenLabel = new Label("Tens:");
        Slider tenSlider = new Slider();
        Text tenTotal = new Text(String.format("%d" ,(int)tenSlider.getValue()));
        tenSlider.setMin(0);
        tenSlider.setMax(cus.t.tens);
        tenSlider.setShowTickLabels(true);
        tenSlider.setShowTickMarks(true);
        tenSlider.setBlockIncrement(1);
        GridPane.setConstraints(tenLabel, 0, 7);
        GridPane.setConstraints(tenSlider, 2, 7);
        GridPane.setConstraints(tenTotal, 3, 7);

        tenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                tenTotal.setText(String.format("%d" ,(int)tenSlider.getValue()));
            }
        });

        Label twentyLabel = new Label("Twenties:");
        Slider twentySlider = new Slider();
        Text twentyTotal = new Text(String.format("%d" ,(int)twentySlider.getValue()));
        twentySlider.setMin(0);
        twentySlider.setMax(cus.t.tens);
        twentySlider.setShowTickLabels(true);
        twentySlider.setShowTickMarks(true);
        twentySlider.setBlockIncrement(1);
        GridPane.setConstraints(twentyLabel, 0, 8);
        GridPane.setConstraints(twentySlider, 2, 8);
        GridPane.setConstraints(twentyTotal, 3, 8);

        twentySlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                twentyTotal.setText(String.format("%d" ,(int)twentySlider.getValue()));
            }
        });

        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 0, 9);

        submit.setOnAction(e -> {
            tran.pennies = ((int)penniesSlider.getValue());
            tran.nickels = ((int)nickelSlider.getValue());
            tran.dimes = ((int)dimeSlider.getValue());
            tran.quarters = ((int)quarterSlider.getValue());
            tran.ones = ((int)oneSlider.getValue());
            tran.fives = ((int)fiveSlider.getValue());
            tran.tens = ((int)tenSlider.getValue());
            tran.twenties = ((int)twentySlider.getValue());
            tranStage.close();
        });

        grid.getChildren().addAll(totalLabel, total, penniesLabel, penniesSlider, pennyTotal, nickelLabel, nickelSlider,
                nickelTotal, dimeLabel, dimeSlider, dimeTotal, quarterLabel, quarterSlider, quarterTotal,
                oneLabel, oneSlider, oneTotal, fiveLabel, fiveSlider, fiveTotal, tenLabel, tenSlider, tenTotal,
                twentyLabel, twentySlider, twentyTotal, submit);

        tranStage.setScene(new Scene(grid, 500, 500));
        tranStage.show();
        return tran;
    }

    private BorderPane build_ice(){
        Label label1=new Label("Which Ice Cream:");
        Label dlabel= new Label("Description:");
        Label plabel= new Label("Price:");
        Button button=new Button("Submit");
        BorderPane layout = new BorderPane();
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        ChoiceBox<IceCreamMain> choice = new ChoiceBox<>();
        TextField dtext = new TextField();
        TextField ptext = new TextField();
        for(int x=0;x<shopper.iceCreamList.size();x++){
            choice.getItems().add(shopper.iceCreamList.get(x));
        }
        button.setOnAction(e->{
            try{
                System.out.println(dtext.getText());
                double p= Double.parseDouble(ptext.getText());
                choice.getValue().description=dtext.getText();
                choice.getValue().price=p;

                stage.setScene(primaryStage());
                //stage.setScene(update_Menu());
            }catch(Exception ex){
                System.err.println("Please enter a double for price!");
            }
        });

        choice.setOnAction(e->{
            dtext.setText(choice.getValue().description);
            ptext.setText(choice.getValue().price+"");




        });
        choice.setValue(shopper.iceCreamList.get(0));
        pane.add(label1,0,0);
        pane.add(choice, 1, 0);
        pane.add(dlabel,0,1);
        pane.add(dtext,1,1);
        pane.add(plabel, 0, 2);
        pane.add(ptext,1,2);
        pane.add(button,1,3);
        pane.setHgap(10.0);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    private BorderPane build_worker(){
        Label label1=new Label("Which worker:");
        Label clabel= new Label("Customers Served:");
        Label sslabel= new Label("Scoops Served:");
        Label mtlabel= new Label("Money Taken:");
        Button button=new Button("Submit");
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        BorderPane layout = new BorderPane();
        ChoiceBox<Worker> choice = new ChoiceBox<>();
        TextField ctext = new TextField();
        TextField sstext = new TextField();
        TextField mttext = new TextField();
        for(int x=0;x<shopper.workerList.size();x++){
            choice.getItems().add(shopper.workerList.get(x));
        }
        button.setOnAction(e->{
            try{
                long ss;
                long c;
                double mt;
                ss=Long.parseLong(sstext.getText());
                mt=Double.parseDouble(mttext.getText());

                c=Long.parseLong(ctext.getText());
                choice.getValue().moneyTaken=mt;
                choice.getValue().scoopsServed=ss;
                choice.getValue().customerServed=c;
                stage.setScene(primaryStage());
                //stage.setScene(update_Menu());
            }catch(Exception ex){
                System.err.println("Values not correct!");
            }
        });

        choice.setOnAction(e->{
            mttext.setText(""+choice.getValue().moneyTaken);
            sstext.setText(""+choice.getValue().scoopsServed);
            ctext.setText(""+choice.getValue().customerServed);

        });
        choice.setValue(shopper.workerList.get(0));



        pane.add(label1,0,0);
        pane.add(choice, 1, 0);
        pane.add(clabel,0,1);
        pane.add(ctext,1,1);
        pane.add(sslabel, 0, 2);
        pane.add(sstext,1,2);
        pane.add(mtlabel, 0, 3);
        pane.add(mttext,1,3);
        pane.add(button,1,4);
        pane.setHgap(10.0);
        pane.setVgap(10.0);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    private BorderPane build_customer(){
        GridPane pane=new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        BorderPane layout = new BorderPane();
        Label label1=new Label("Which Customer:");
        Label wlabel= new Label("Customer Money:");
        Label lhlabel = new Label("Level of Happiness:");
        TextField lhtext= new TextField();
        TextField wtext= new TextField();
        //Label sslabel= new Label("Scoops Served:");
        //Label mtlabel= new Label("Money Taken:");
        Button button=new Button("Submit");
        ChoiceBox<Customer>choice=new ChoiceBox<>();
        for(int x=0;x<shopper.customerList.size();x++)choice.getItems().add(shopper.customerList.get(x));
        button.setOnAction(e->{
            try{
                int l=Integer.parseInt(lhtext.getText());
                double w = Double.parseDouble(wtext.getText());
                choice.getValue().levelOfHappiness=l;
                choice.getValue().wallet=w;
                stage.setScene(primaryStage());
                //stage.setScene(update_Menu());
            }catch(Exception ex){
                System.err.println("TRY again!");
            }
        });



        choice.setOnAction(e->{
            lhtext.setText(choice.getValue().levelOfHappiness+"");
            wtext.setText(choice.getValue().wallet+"");

        });
        choice.setValue(shopper.customerList.get(0));
        pane.add(label1,0,0);
        pane.add(choice, 1, 0);
        pane.add(wlabel,0,1);
        pane.add(wtext,1,1);
        pane.add(lhlabel, 0, 2);
        pane.add(lhtext,1,2);
        pane.add(button, 1, 3);
        pane.setHgap(10.0);
        pane.setVgap(10.0);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    private BorderPane pay_order(){
        BorderPane layout = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        layout.setCenter(grid);
        layout.setTop(menubar);
        for (int i = 0; i < shopper.workerList.size(); i++) {
            if (shopper.workerList.get(i).getClass().getSimpleName().equals("Cashier")) {
                if (!shopper.workerList.get(i).getActive()) {
                    grid.add(new Label("No cashier active!"),0,0);
                    return layout;
                }
            }
        }
        if(shopper.orderList.isEmpty()){
            grid.add(new Label("No order created!"),0,0);
            return layout;
        }
        Button button = new Button("Submit");



        Label label = new Label("Pay Order:");
        ChoiceBox<Order> choice = new ChoiceBox();
        for(int x=0;x<shopper.orderList.size();x++)
            choice.getItems().add(shopper.orderList.get(x));

        Label cashLabel = new Label("Cash:");
        CheckBox cashCheck = new CheckBox();
        GridPane.setConstraints(cashLabel, 0, 1);
        GridPane.setConstraints(cashCheck, 1, 1);

        Label creditLabel = new Label("Credit:");
        CheckBox creditCheck = new CheckBox();
        GridPane.setConstraints(creditLabel, 0, 2);
        GridPane.setConstraints(creditCheck, 1, 2);

        cashCheck.setOnAction(e -> {
            creditCheck.setSelected(false);
        });
        creditCheck.setOnAction(e -> {
            cashCheck.setSelected(false);
        });
        grid.getChildren().addAll(cashLabel, cashCheck, creditLabel, creditCheck);

        button.setOnAction(e->{
            stage.setScene(primaryStage());
            if (cashCheck.isSelected()) {
                try {
                    Transaction tran = new Transaction();
                    tran = createTransaction(choice.getValue(), choice.getValue().c);
                    shopper.pay_order(choice.getValue(), tran);
                }catch(MoneyException ex){
                    System.out.println(ex);
                }
            }
            else {
                shopper.cr.ShopMoney += choice.getValue().getTotalCost();
                shopper.workerList.get(shopper.workerList.indexOf(choice.getValue().w)).moneyTaken +=
                        choice.getValue().getTotalCost();
            }

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }


    private BorderPane place_order(){
        //shopper.servingList.add(new Serving("Dog Shit"));
        BorderPane layout = new BorderPane();
        GridPane grid = new GridPane();
        ArrayList<Serving> servingList = new ArrayList<>();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        layout.setCenter(grid);
        layout.setTop(menubar);
        if(shopper.customerList.isEmpty()){
            Label temp = new Label("No Customer Created");
            grid.add(temp,0,0);
            return layout;
        }
        else if(shopper.workerList.isEmpty()){
            Label temp = new Label("No Worker Created");
            grid.add(temp,0,0);
            return layout;
        }
        else if(shopper.iceCreamList.isEmpty()){
            Label temp = new Label("No Ice Cream Created");
            grid.add(temp,0,0);
            return layout;
        }

        Label wl= new Label("Worker:");
        ChoiceBox<Worker> wc = new ChoiceBox<>();
        for(int x=0;x<shopper.workerList.size();x++){

            if(shopper.workerList.get(x).typeOfWorker!="Stocker" && !shopper.workerList.get(x).getOnBreak()) {
                wc.getItems().add(shopper.workerList.get(x));
                wc.setValue(shopper.workerList.get(x));
            }
        }
        GridPane.setConstraints(wl, 0, 0);
        GridPane.setConstraints(wc, 1, 0);

        Label cl = new Label("Customer:");
        ChoiceBox<Customer> cc = new ChoiceBox<>();
        for(int x=0;x<shopper.customerList.size();x++){

            cc.getItems().add(shopper.customerList.get(x));
        }
        GridPane.setConstraints(cl, 0, 1);
        GridPane.setConstraints(cc, 1, 1);

        Label addLabel = new Label("Add to Order:");
        ChoiceBox<String> addChoice= new ChoiceBox<>();
        addChoice.getItems().addAll("Ice Cream Cone", "Ice Cream Sundae", "Banana Split", "Ice Cream Soda",
                "Root Beer Float");
        addChoice.setValue("Ice Cream Cone");
        Button addButton = new Button("Add");
        GridPane.setConstraints(addLabel, 0, 2);
        GridPane.setConstraints(addChoice, 1, 2);
        GridPane.setConstraints(addButton, 2, 2);
        addButton.setOnAction(e -> {
            switch (addChoice.getValue()) {
                case "Ice Cream Cone":
                    servingList.add(addIceCreamCone());
                    break;
                case "Ice Cream Sundae":
                    servingList.add(addIceCreamSundae());
                    break;
                case "Banana Split":
                    servingList.add(addBananaSplit());
                    break;
                case "Ice Cream Soda":
                    servingList.add(addIceCreamSoda());
                    break;
                case "Root Beer Float":
                    servingList.add(addRootBeerFloat());
                    break;
            }
        });

        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 3);
        submit.setOnAction(e -> {
            Order ord = new Order(cc.getValue(), wc.getValue(), servingList, rand);
            shopper.orderList.add(ord);
            stage.setScene(primaryStage());
        });

        grid.getChildren().addAll(wc, wl, cc, cl, addLabel, addChoice, addButton, submit);
        return layout;
    }

    public IceCreamCone addIceCreamCone(){
        Stage iccStage = new Stage();
        iccStage.setTitle("Add Ice Cream Cone");
        GridPane grid = new GridPane();
        IceCreamCone icc = new IceCreamCone();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label firstScoopLabel = new Label("1st Scoop:");
        ChoiceBox<IceCreamMain> firstScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(firstScoopLabel, 0, 0);
        GridPane.setConstraints(firstScoopChoice, 1, 0);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            firstScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label secondScoopLabel = new Label("2nd Scoop:");
        ChoiceBox<IceCreamMain> secondScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(secondScoopLabel, 0, 1);
        GridPane.setConstraints(secondScoopChoice,1, 1);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            secondScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label thirdScoopLabel = new Label("3rd Scoop:");
        ChoiceBox<IceCreamMain> thirdScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(thirdScoopLabel, 0, 2);
        GridPane.setConstraints(thirdScoopChoice, 1, 2);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            thirdScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label coneTypeLabel = new Label("Type of Cone:");
        ChoiceBox<String> coneTypeChoice = new ChoiceBox<>();
        GridPane.setConstraints(coneTypeLabel, 0, 3);
        GridPane.setConstraints(coneTypeChoice, 1, 3);

        coneTypeChoice.getItems().addAll("Cake Cone", "Sugar Cone", "Waffle Cone");
        coneTypeChoice.setValue("Cake Cone");

        Button add = new Button("Add");
        GridPane.setConstraints(add, 1, 4);
        add.setOnAction(e -> {
            if (firstScoopChoice != null && secondScoopChoice == null && thirdScoopChoice == null) {
                icc.numberOfScoops = 1;
                icc.flavors[0] = firstScoopChoice.getValue();
            }
            else if (firstScoopChoice != null && secondScoopChoice != null && thirdScoopChoice == null) {
                icc.numberOfScoops = 2;
                icc.flavors[0] = firstScoopChoice.getValue();
                icc.flavors[1] = secondScoopChoice.getValue();
            }
            else if (firstScoopChoice != null && secondScoopChoice != null && thirdScoopChoice != null) {
                icc.numberOfScoops = 3;
                icc.flavors[0] = firstScoopChoice.getValue();
                icc.flavors[1] = secondScoopChoice.getValue();
                icc.flavors[2] = thirdScoopChoice.getValue();
            }
            icc.coneType = coneTypeChoice.getValue();
            iccStage.close();
        });

        grid.getChildren().addAll(firstScoopLabel, firstScoopChoice, secondScoopLabel, secondScoopChoice,
                thirdScoopLabel, thirdScoopChoice, coneTypeLabel, coneTypeChoice, add);

        iccStage.setScene(new Scene(grid, 300, 300));
        iccStage.show();
        return icc;
    }

    public IceCreamSundae addIceCreamSundae() {
        IceCreamSundae ics = new IceCreamSundae();
        Stage icsStage = new Stage();
        icsStage.setTitle("Add Ice Cream Sundae");
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label firstScoopLabel = new Label("1st Scoop:");
        ChoiceBox<IceCreamMain> firstScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(firstScoopLabel, 0, 0);
        GridPane.setConstraints(firstScoopChoice, 1, 0);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            firstScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label secondScoopLabel = new Label("2nd Scoop:");
        ChoiceBox<IceCreamMain> secondScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(secondScoopLabel, 0, 1);
        GridPane.setConstraints(secondScoopChoice,1, 1);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            secondScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label syrupLabel = new Label("Type of Syrup:");
        ChoiceBox<String> syrupChoice = new ChoiceBox<>();
        GridPane.setConstraints(syrupLabel, 0, 2);
        GridPane.setConstraints(syrupChoice, 1, 2);

        syrupChoice.getItems().addAll("Strawberry Syrup", "Chocolate Syrup", "Marshmallow Cream", "Pineapple",
                "Ketchup", "Mustard", "Pickle Relish");
        syrupChoice.setValue("Strawberry Syrup");

        Label nutLabel = new Label("Add Nuts:");
        CheckBox nutCheck = new CheckBox();
        GridPane.setConstraints(nutLabel, 0, 3);
        GridPane.setConstraints(nutCheck, 1, 3);

        Button add = new Button("Add");
        GridPane.setConstraints(add, 1, 4);
        add.setOnAction(e -> {
            if (firstScoopChoice != null && secondScoopChoice == null) {
                ics.numberOfScoops = 1;
                ics.flavors[0] = firstScoopChoice.getValue();
            }
            else if (firstScoopChoice != null && secondScoopChoice != null) {
                ics.numberOfScoops = 2;
                ics.flavors[0] = firstScoopChoice.getValue();
                ics.flavors[1] = secondScoopChoice.getValue();
            }

            ics.syrup[0] = syrupChoice.getValue();

            if (nutCheck.isSelected()) {
                ics.nuts = true;
            }
            icsStage.close();
        });

        grid.getChildren().addAll(firstScoopLabel, firstScoopChoice, secondScoopLabel, secondScoopChoice,
                syrupLabel, syrupChoice, nutLabel, nutCheck, add);
        icsStage.setScene(new Scene(grid, 300, 300));
        icsStage.show();
        return ics;
    }

    public BananaSplit addBananaSplit() {
        BananaSplit bs = new BananaSplit();
        Stage bsStage = new Stage();
        bsStage.setTitle("Add Banana Split");
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label firstScoopLabel = new Label("1st Scoop:");
        ChoiceBox<IceCreamMain> firstScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(firstScoopLabel, 0, 0);
        GridPane.setConstraints(firstScoopChoice, 1, 0);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            firstScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label secondScoopLabel = new Label("2nd Scoop:");
        ChoiceBox<IceCreamMain> secondScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(secondScoopLabel, 0, 1);
        GridPane.setConstraints(secondScoopChoice,1, 1);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            secondScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label thirdScoopLabel = new Label("3rd Scoop:");
        ChoiceBox<IceCreamMain> thirdScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(thirdScoopLabel, 0, 2);
        GridPane.setConstraints(thirdScoopChoice, 1, 2);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            thirdScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label syrupLabel1 = new Label("Type of Syrup:");
        ChoiceBox<String> syrupChoice1 = new ChoiceBox<>();
        GridPane.setConstraints(syrupLabel1, 0, 3);
        GridPane.setConstraints(syrupChoice1, 1, 3);

        syrupChoice1.getItems().addAll("Strawberry Syrup", "Chocolate Syrup", "Marshmallow Cream", "Pineapple",
                "Ketchup", "Mustard", "Pickle Relish");
        syrupChoice1.setValue("Strawberry Syrup");

        Label syrupLabel2 = new Label("Type of Syrup:");
        ChoiceBox<String> syrupChoice2 = new ChoiceBox<>();
        GridPane.setConstraints(syrupLabel2, 0, 4);
        GridPane.setConstraints(syrupChoice2, 1, 4);

        syrupChoice2.getItems().addAll("Strawberry Syrup", "Chocolate Syrup", "Marshmallow Cream", "Pineapple",
                "Ketchup", "Mustard", "Pickle Relish");
        syrupChoice2.setValue("Strawberry Syrup");

        Label syrupLabel3 = new Label("Type of Syrup:");
        ChoiceBox<String> syrupChoice3 = new ChoiceBox<>();
        GridPane.setConstraints(syrupLabel3, 0, 5);
        GridPane.setConstraints(syrupChoice3, 1, 5);

        syrupChoice3.getItems().addAll("Strawberry Syrup", "Chocolate Syrup", "Marshmallow Cream", "Pineapple",
                "Ketchup", "Mustard", "Pickle Relish");
        syrupChoice3.setValue("Strawberry Syrup");

        Label nutLabel = new Label("Add Nuts:");
        CheckBox nutCheck = new CheckBox();
        GridPane.setConstraints(nutLabel, 0, 6);
        GridPane.setConstraints(nutCheck, 1, 6);

        Button add = new Button("Add");
        GridPane.setConstraints(add, 1, 7);

        add.setOnAction(e -> {
            if (firstScoopChoice != null && secondScoopChoice == null && thirdScoopChoice == null) {
                bs.numberOfScoops = 1;
                bs.flavors[0] = firstScoopChoice.getValue();
            }
            else if (firstScoopChoice != null && secondScoopChoice != null && thirdScoopChoice == null) {
                bs.numberOfScoops = 2;
                bs.flavors[0] = firstScoopChoice.getValue();
                bs.flavors[1] = secondScoopChoice.getValue();
            }
            else if (firstScoopChoice != null && secondScoopChoice != null && thirdScoopChoice != null) {
                bs.numberOfScoops = 3;
                bs.flavors[0] = firstScoopChoice.getValue();
                bs.flavors[1] = secondScoopChoice.getValue();
                bs.flavors[2] = thirdScoopChoice.getValue();
            }
            bs.syrup[0] = syrupChoice1.getValue();
            bs.syrup[1] = syrupChoice2.getValue();
            bs.syrup[2] = syrupChoice3.getValue();

            if (nutCheck.isSelected()) {
                bs.nuts = true;
            }
            bsStage.close();
        });

        grid.getChildren().addAll(firstScoopLabel, firstScoopChoice, secondScoopLabel, secondScoopChoice,
                thirdScoopLabel, thirdScoopChoice, syrupLabel1, syrupChoice1, syrupLabel2, syrupChoice2,
                syrupLabel3, syrupChoice3, nutLabel, nutCheck, add);
        bsStage.setScene(new Scene(grid, 300, 300));
        bsStage.show();
        return bs;
    }

    public IceCreamSoda addIceCreamSoda() {
        IceCreamSoda ics = new IceCreamSoda();
        Stage icsStage = new Stage();
        icsStage.setTitle("Add Ice Cream Soda");
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label firstScoopLabel = new Label("Flavor:");
        ChoiceBox<IceCreamMain> firstScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(firstScoopLabel, 0, 0);
        GridPane.setConstraints(firstScoopChoice, 1, 0);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            firstScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Button add = new Button("Add");
        GridPane.setConstraints(add, 1, 1);

        add.setOnAction(e -> {
            ics.flavors[0] = firstScoopChoice.getValue();
            icsStage.close();
        });

        grid.getChildren().addAll(firstScoopLabel, firstScoopChoice, add);
        icsStage.setScene(new Scene(grid, 300, 300));
        icsStage.show();
        return ics;
    }

    public RootBeerFloat addRootBeerFloat() {
        RootBeerFloat rbf = new RootBeerFloat();
        return rbf;
    }

    private BorderPane active_cashier(){
        GridPane grid = new GridPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(grid);
        layout.setTop(menubar);
        Button button = new Button("Submit");

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);


        Label label = new Label("Set Cashier Active:");
        ChoiceBox<Cashier> choice = new ChoiceBox<>();
        for(int x=0;x<shopper.workerList.size();x++){
            if(shopper.workerList.get(x).getClass().getSimpleName().equals("Cashier")){
                if(shopper.workerList.get(x).getOnBreak()) {
                    choice.getItems().add((Cashier)shopper.workerList.get(x));
                }
            }
        }


        button.setOnAction(e->{
            if(choice.getValue()!=null) {

                choice.getValue().setActive();
            }

            stage.setScene(primaryStage());

        });


        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }

    private BorderPane active_stocker(){
        GridPane grid = new GridPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(grid);
        layout.setTop(menubar);
        Button button = new Button("Submit");

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);


        Label label = new Label("Set Stocker Active:");
        ChoiceBox<Stocker> choice = new ChoiceBox();
        for(int x=0;x<shopper.workerList.size();x++){
            if(shopper.workerList.get(x).getClass().getSimpleName().equals("Stocker")){
                if (shopper.workerList.get(x).getOnBreak()){
                    choice.getItems().add((Stocker)shopper.workerList.get(x));
                }
            }
        }


        button.setOnAction(e->{
            if(choice.getValue()!=null){

                choice.getValue().setActive();
            }

            stage.setScene(primaryStage());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }

    private BorderPane set_cashier_break(){
        GridPane grid = new GridPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(grid);
        layout.setTop(menubar);
        Button button = new Button("Submit");

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);



        Label label = new Label("Set Cashier On Break:");
        ChoiceBox<Cashier> choice = new ChoiceBox();
        for(int x=0;x<shopper.workerList.size();x++){
            if(shopper.workerList.get(x).getClass().getSimpleName().equals("Cashier")){
                if (shopper.workerList.get(x).getActive()) {
                    choice.getItems().add((Cashier)shopper.workerList.get(x));
                }
            }
        }


        button.setOnAction(e->{
            if(choice.getValue()!=null) {
                choice.getValue().setOnBreak();
                /*
                if(choice.getValue().getOnBreak())
                    choice.getValue().onBreak=false;
                else choice.getValue().onBreak=true;
                */
            }
            stage.setScene(primaryStage());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }



    private BorderPane set_stocker_break(){
        GridPane grid = new GridPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(grid);
        layout.setTop(menubar);
        Button button = new Button("Submit");

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);



        Label label = new Label("Set Stocker On Break:");
        ChoiceBox<Stocker> choice = new ChoiceBox();
        for(int x=0;x<shopper.workerList.size();x++){
            if(shopper.workerList.get(x).getClass().getSimpleName().equals("Stocker")) {
                if (shopper.workerList.get(x).getActive()) {
                    choice.getItems().add((Stocker)shopper.workerList.get(x));
                }
            }
        }


        button.setOnAction(e->{
            if(choice.getValue()!=null) {
                choice.getValue().setOnBreak();
                /*
                if(choice.getValue().getOnBreak())
                    choice.getValue().onBreak=false;
                else choice.getValue().onBreak=true;
                */
            }
            stage.setScene(primaryStage());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }

    public BorderPane aboutPage() {
        System.out.println("IN ABOUT");
        BorderPane layout = new BorderPane();
        Text aboutText = new Text("1) Neil Malhotra\n" +
                "Student ID-1001084075\n" +
                "2) Jason Breedlove\n" +
                "Student ID-1001082951\n" +
                "3) Emmanuel Lennix\n" +
                "Student ID-10001104990\n" +
                "About\n" +
                "\n" +
                "We made this project using JAVA FX.\n" +
                "We put in a lot of effort and time into this project.\n" +
                "We feel that this project has helped us understand JAVA GUIs\n" +
                "very well and will surely help us in our respective professional fields.\n" +
                "We hope you like it!!");
        layout.setTop(menubar);
        layout.setCenter(aboutText);
        //layout.getChildren().addAll(aboutText);

        return layout;
    }
}
