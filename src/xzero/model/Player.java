package xzero.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import xzero.model.events.PlayerActionEvent;
import xzero.model.events.PlayerActionListener;

/**
 *  Игрок, который размещает предложенную ему метку
 */
public class Player {
    
// --------------------------------- Имя игрока -------------------------------    
    private String _name;
    
    public void setName(String name) {
        _name = name;
    }
    
    public String name() {
        return _name;
    }

 // ----------------------- Устанавливаем связь с полем -----------------------   
    private GameField _field;
    
    public Player (GameField field, String name) {
        _field = field;
        _name = name;
    }
    
// ---------------------- Метка, которую нужно установить -----------------------    
    private Label _label;
    
    public void setActiveLabel(Label l) {
        _label = l;
        _label.setPlayer(this);
        
        // Генерируем событие
        fireLabelIsReceived(l);
    }

    public Label activeLabel() {
        return _label;
    }
    
    public void setLabelTo(Point pos){
        if (_label == null) {
            throw new IllegalStateException("Активная метка не задана.");
        }
        // TODO породить исключение, если не задана активная метка 
        
        _field.setLabel(pos, _label);
        
        // Генерируем событие
        fireLabelIsPlaced(_label);

        // Повторно использовать метку нельзя
        _label = null;
    }
    
    private ArrayList<Label> _labels = new ArrayList<>();
    
    public List<Label> labels(){
        
        _labels.clear();
        for(Label obj: _field.labels())
        {
            if(obj.player().equals(this))
            { _labels.add(obj); }
        }
        
        return Collections.unmodifiableList(_labels);
    }   
    
    // ---------------------- Порождает события -----------------------------

    private ArrayList<PlayerActionListener> _playerListenerList = new ArrayList<>();
 
    // Присоединяет слушателя
    public void addPlayerActionListener(PlayerActionListener l) {
        _playerListenerList.add(l);
    }
    
    // Отсоединяет слушателя
    public void removePlayerActionListener(PlayerActionListener l) { 
        _playerListenerList.remove(l);
    } 
    
    // Оповещает слушателей о событии
    protected void fireLabelIsPlaced(Label l) {
        PlayerActionEvent pae = new PlayerActionEvent(this);
        pae.setLabel(l);
        pae.setPlayer(this);
        for (PlayerActionListener listener : _playerListenerList) {
            listener.labelisPlaced(pae);
        }
    }     

    protected void fireLabelIsReceived(Label l) {
        PlayerActionEvent pae = new PlayerActionEvent(this);
        pae.setLabel(l);
        pae.setPlayer(this);
        for (PlayerActionListener listener : _playerListenerList) {
            listener.labelIsReceived(pae);
        }
    }     
}
