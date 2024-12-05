package xzero.model;

/**
 * Метка, которую можно поместить на поле
 */
public class Label {
    
// --------------- Ячейка, которой прнадлежит метка. Задает сама ячейка -------
    private Cell _cell = null;

    public void setCell(Cell cell) {
        if (cell == _cell) {
            return;
        }
        _cell = cell;
        cell.setLabel(this);
    }

    public void unsetCell() {
        if (_cell == null) {
            return;
        }
        Cell buf = _cell;
        _cell = null;
        buf.unsetLabel();
    }
    
    public Cell cell(){
        return _cell;
    }
    
// - Игрок, которому прнадлежит метка. Метка может быть нейтральной (не принадлежать никому) -
    
    private Player _player = null;
    
    void setPlayer(Player p){
        _player = p;
    }

    void unsetPlayer(){
        _player = null;
    }
    
    public Player player(){
        return _player;
    } 
}
