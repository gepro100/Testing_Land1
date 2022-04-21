
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class Tic_Tac_Toe {
    //Display the Field
    private static void display_playfield(int[] field){
        int i = 0;
        for(int z = 0; z<3; z++){
            for(int s = 0; s<3; s++){
                System.out.print(field[i]+" ");
                i++;
            }
            System.out.println();
        }
        return;
    }

    //Computer Move
    //Random Element of Set (null if Set is empty or Error)
    static <Integer> Integer getRandomSetElement(Set<Integer> set) {
        return set.stream().skip(new Random().nextInt(set.size())).findFirst().orElse(null);
    }
    //Counting Empty fields
    private static int generate_computer_move(int[] field){
        Set<Integer> empty = new HashSet<Integer>();
        for(int i = 0; i<9; i++){
            if(field[i] == 0){
                empty.add(i);
            }
        }
        return getRandomSetElement(empty);
    }

    //Player Move
    private static int get_player_move(int[] field){
        Scanner in = new Scanner(System.in);
        System.out.println("Your turn! Write the fieldnumber. The fields are numberated:\n"+
        "0 1 2\n3 4 5\n6 7 8\n");
        String userChoice = in.nextLine();
        return Integer.parseInt(userChoice);
    }

    //Who starts the game? (use avaiable Methods for cleaner code)
    private static boolean toss_coin(){
        Set<Integer> sides = new HashSet<Integer>();
        sides.add(1);
        sides.add(2);
        return (getRandomSetElement(sides)==1);
    }

    //did sb. win? if yes who won?
    private static int check_winner(int[] field){
        //won by row
        for(int z = 1; z<8; z= z+3){
            if(field[z] == 0){
                break;
            }
            if(field[z-1] == field[z+1] && field[z+1] == field[z]){
                return field[z];
            }
        }
        //won by columb
        for(int s = 3; s<6; s++){
            if(field[s] == 0){
                break;
            }
            if(field[s-3] == field[s+3] && field[s] == field[s-3]){
                return field[s];
            }
        }
        //won by diagonal
        if(field[0] != 0){
            if(field[0] == field[4] && field[4] == field[8]){
                return field[0];
            }
        }
        //won by other diagonal
        if(field[2] != 0){
            if(field[2] == field[4] && field[6] == field[4]){
                return field[2];
            }
        }
        //no victor yet
        return 0;
    }

    //Happy Computer
    private static void computer_wins(){
        System.out.println("Ahahaha, I knew you wouldnt win.\n Youre Lucky, im in a good mood :)\n"+
        "I will give you a new chance.\nLet us pretend nothing happened so far.\n");
        return;
    }

    //Sad Computer
    private static void player_wins(){
        System.out.println("You have Won Damit! DAAAAAMIT, im so angry!\n"+
        "I cant handle this!\nOh.., i know what to do :D\nMUHAHAHAHAHA\n"+
        "Ill erase my Memory!\n");
        return;
    }

    //This Runs one Game
    private static void Game_loop(){
        //Prepare game
        boolean game_over = false;
        int[] field = new int[9];
        for(int i = 0; i<9; i++){
            field[i] = 0;
        }

        //Start Game
        System.out.println("Lets decide, who starts, ill toss a coin:\n"+
        "Head means you otherwise the computer Starts.\n\n");
        if(toss_coin()){
            System.out.print("Its Head, you go first");
        //when the computer starts:
        }else{
            System.out.print("Hehe, the Computer starts\n");
            int p = generate_computer_move(field);
            field[p] = 2;
        }
        //The Game_loop
        for(int i = 0 ; i < 9; i++){
            System.out.println("\nHere is the Playfield:\n");
            display_playfield(field);
            int p = get_player_move(field);
            field[p] = 1;
            if(check_winner(field)==1){
                player_wins();
                return;
            }
            p = generate_computer_move(field);
            field[p] = 2;
            if(check_winner(field) == 2){
                computer_wins();
                return;
            }
        }
        computer_wins();
        return;
    }

    public static void main(String[] args){
        boolean play = true;
        Scanner in = new Scanner(System.in);
        while(play){
            System.out.println("Hi there, do you want to play some Tic-Tac-Toe against random Moves?");
            System.out.println("yes or no? What do you say?");
            String userChoice = in.nextLine();
            if(userChoice.equals("yes")){
                Game_loop();
            }else if (userChoice.equals("no")){
                System.out.println("Oh... Okay...\n well... then Goodbye!");
                play = false;
            }else{
                System.out.println("You wrote: "+ userChoice);
                System.out.println("Dou you speaaake my language o.O??\n Ill repeat...\n");
            }
        }
        return;
    }

}
