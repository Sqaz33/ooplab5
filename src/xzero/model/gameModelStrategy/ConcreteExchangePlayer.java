package xzero.model.gameModelStrategy;

import xzero.model.GameModel;
import xzero.model.Label;

public class ConcreteExchangePlayer implements ExchangePlayerStrategy {

    @Override
    public void exchangePlayer(GameModel model) {
        model.moveActivePlayer();
        // Выбрать для него метку
        Label newLabel = model.labelFactory().createLabel();
        model.activePlayer().setActiveLabel(newLabel);
    }
}
