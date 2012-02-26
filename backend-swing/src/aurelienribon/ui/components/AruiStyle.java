package aurelienribon.ui.components;

import aurelienribon.ui.components.processors.JComponentProcessor;
import aurelienribon.ui.css.Style;
import javax.swing.JComponent;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class AruiStyle {

	/**
	 * Registers all the properties, functions and processors of the Arui
	 * backend to the CSS engine. Requires the Swing backend to be initialized.
	 */
	public static void init() {
		Style.registerProperty(AruiProperties.foregroundMouseOver);
		Style.registerProperty(AruiProperties.foregroundMouseDown);
		Style.registerProperty(AruiProperties.foregroundSelected);
		Style.registerProperty(AruiProperties.foregroundUnselected);
		Style.registerProperty(AruiProperties.stroke);
		Style.registerProperty(AruiProperties.strokeMouseOver);
		Style.registerProperty(AruiProperties.strokeMouseDown);
		Style.registerProperty(AruiProperties.strokeSelected);
		Style.registerProperty(AruiProperties.strokeUnselected);
		Style.registerProperty(AruiProperties.fill);
		Style.registerProperty(AruiProperties.fillMouseOver);
		Style.registerProperty(AruiProperties.fillMouseDown);
		Style.registerProperty(AruiProperties.fillSelected);
		Style.registerProperty(AruiProperties.fillUnselected);
		Style.registerProperty(AruiProperties.corderRadius);
		Style.registerProperty(AruiProperties.borderBody);
		Style.registerProperty(AruiProperties.borderHeader);
		Style.registerProperty(AruiProperties.borderFill);
		Style.registerProperty(AruiProperties.borderMargin);
		Style.registerProperty(AruiProperties.borderTitle);
		Style.registerProperty(AruiProperties.borderIcon);
		Style.registerProperty(AruiProperties.borderFont);
		Style.registerProperty(AruiProperties.borderForeground);

		Style.registerFunction(AruiFunctions.groupBorder);

		Style.registerProcessor(JComponent.class, new JComponentProcessor());
		Style.registerProcessor(Button.class, new Button.Processor());
		Style.registerProcessor(TabPanel.class, new TabPanel.Processor());
		Style.registerProcessor(TabPanelHeaderPanel.class, new TabPanelHeaderPanel.Processor());
		Style.registerProcessor(TabPanelHeaderSubPanel.class, new TabPanelHeaderSubPanel.Processor());
	}
}
