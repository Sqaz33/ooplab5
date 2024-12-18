package xzero.model.gameModelStrategy;

import xzero.model.GameModel;
import xzero.model.Label;
import xzero.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExchangePlayerNSwapLabel extends ChainedExchangePlayerStrategy {
    public ExchangePlayerNSwapLabel(ExchangePlayerStrategy next) {
        super(next);
    }

    @Override
    public void exchangePlayer(GameModel model) {
        //------------------------------------
        Random random = new Random();
        int val = random.nextInt(2);
        if (val == 1) {
            int randomIndex;
            ArrayList<Player> players = model.playerList();
            Player randomPlayer = null;
            if (!players.isEmpty()) {
                randomIndex = random.nextInt(players.size());
                randomPlayer = players.get(randomIndex);
            }

            List<Label> labels = model.field().labels();
            Label randomLabel = null;
            if (!labels.isEmpty()) {
                randomIndex = random.nextInt(labels.size());
                randomLabel = labels.get(randomIndex);
            }

            if (randomLabel != null && randomPlayer != null && randomPlayer != model.activePlayer()) {
                randomPlayer.setActiveLabel(randomLabel);
                randomPlayer.setLabelTo(randomLabel.cell().position());
            }
        }
        next().exchangePlayer(model);
    }
}
