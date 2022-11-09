package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;

import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.io.IOException;
import java.util.Objects;

public class ShowInfoCommand implements MenuItem {
    CreateInsCommand createInsCommand;
    @Override
    public boolean execute() throws IOException {
        printInfo();
        createInsCommand = getCreateInsCommand();
        createInsCommand.execute();
        return true;
    }
    private void printInfo(){
        System.out.println("*".repeat(60));
        System.out.println("\n* Особисте страхування - форма захисту від ризиків, що загрожують життю, здоров’ю та працездатності людини\n");
        System.out.println("* Майнове страхування - форма захисту від ризиків, які не носять навмисного характеру та загрожують майну\n" +
                "чи групі речей від втрати (знищення) чи пошкодження, а також підприємницьких ризиків.\n");
        System.out.println("* Страхування відповідальності -  формаа захист економічних інтересів страхувальників, здатних заподіяти \n" +
                "шкоду третім особам.\n");
    }
    public void setCreateInsCommand(CreateInsCommand createInsCommand) {
        this.createInsCommand = createInsCommand;
    }

    public CreateInsCommand getCreateInsCommand() {
        return Objects.requireNonNullElseGet(createInsCommand, CreateInsCommand::new);
    }
}
