package xzero.model.gameModelStrategy;

import xzero.model.GameModel;

abstract public class ChainedExchangePlayerStrategy implements ExchangePlayerStrategy {
    @Override
    abstract public void exchangePlayer(GameModel model);

    private ExchangePlayerStrategy _next;

    protected ChainedExchangePlayerStrategy() {
        _next = null;
    }

    protected ChainedExchangePlayerStrategy(ExchangePlayerStrategy next) {
        _next = next;
    }

    protected ExchangePlayerStrategy next() {
        return _next;
    }
}
