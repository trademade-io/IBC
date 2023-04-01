package ibcalpha.ibc;

import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class ConfigureBypassOrderPrecautionsTask  implements ConfigurationAction{

    private final boolean bypassOrderPrecautions;
    private JDialog configDialog;

    ConfigureBypassOrderPrecautionsTask(boolean bypassOrderPrecautions) {
        this.bypassOrderPrecautions = bypassOrderPrecautions;
    }

    @Override
    public void run() {
        try {
            Utils.logToConsole("Setting BypassOrderPrecautions");

            Utils.selectConfigSection(configDialog, new String[] {"API", "Precautions"});

            JCheckBox bypassOrderPrecautionsCheckbox = SwingUtils.findCheckBox(configDialog, "Bypass Order Precautions for API Orders");
            if (bypassOrderPrecautionsCheckbox == null) {
                // NB: we don't throw here because older TWS versions did not have this setting
                Utils.logError("could not find Bypass Order Precautions for API Orders checkbox");
                return;
            }

            if (bypassOrderPrecautionsCheckbox.isSelected() == bypassOrderPrecautions) {
                Utils.logToConsole("Bypass Order Precautions for API Orders checkbox is already set to: " + bypassOrderPrecautions);
            } else {
				bypassOrderPrecautionsCheckbox.setSelected(bypassOrderPrecautions);
				Utils.logToConsole("Bypass Order Precautions for API Orders checkbox is now set to: " + bypassOrderPrecautions);
            }
        } catch (IbcException e) {
            Utils.logException(e);
        }
    }

    @Override
    public void initialise(JDialog configDialog) {
        this.configDialog = configDialog;
    }

}
