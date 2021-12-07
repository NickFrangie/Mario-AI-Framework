package agents.FrangieSlatteryMcEvoyAgent;

import java.util.ArrayList;
import java.util.Random;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

import engine.helper.GameStatus;

public class Agent implements MarioAgent {
    private Random rnd;
    private ArrayList<boolean[]> choices;
    float greed; //collect coins
    float fightorflight; //high to kill enemies, low to run away
    float frog; //jumps
    float smash; //breaks blocks
    float boost; //collects powerups
    float sadness; //dies
    float zoomer; //gotta go fast


    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        rnd = new Random();
        choices = new ArrayList<>();
        //right run
        choices.add(new boolean[]{false, true, false, true, false});
        //right jump and run
        choices.add(new boolean[]{false, true, false, true, true});
        // right
        choices.add(new boolean[]{false, true, false, false, false});
        // right jump
        choices.add(new boolean[]{false, true, false, false, true});
        //left
        choices.add(new boolean[]{true, false, false, false, false});
        //left run
        choices.add(new boolean[]{true, false, false, true, false});
        //left jump
        choices.add(new boolean[]{true, false, false, false, true});
        //left jump and run
        choices.add(new boolean[]{true, false, false, true, true});

        greed = rnd.nextFloat(); //collect coins
        fightorflight = rnd.nextFloat(); //high to kill enemies, low to run away
        frog = rnd.nextFloat(); //jumps
        smash = rnd.nextFloat(); //breaks blocks
        boost = rnd.nextFloat(); //collects powerups
        sadness = rnd.nextFloat(); //dies
        zoomer = rnd.nextFloat(); //gotta go fast
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        return choices.get(rnd.nextInt(choices.size()));
    }

    @Override
    public String getAgentName() {
        return "RandomAgent";
    }

    int percentChance(float[] list) {
        float total = 0f;
        for (int i = 0; i < list.length; i++) {
            total += list[i];
        }
        float random = rnd.nextFloat();
        for (int i = 0; i < list.length; i++) {
            if (random <= list[i]/total) return i;
            else random -= list[i]/total;
        }
        return -1;
    }
}
