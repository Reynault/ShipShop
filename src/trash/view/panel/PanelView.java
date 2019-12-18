package trash.view.panel;

import java.util.Observer;
import java.util.ResourceBundle;

public interface PanelView extends Observer {
    void initialize(ResourceBundle resources);
}
