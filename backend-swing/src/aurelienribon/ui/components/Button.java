package aurelienribon.ui.components;

import aurelienribon.ui.css.Container;
import aurelienribon.ui.css.DeclarationSet;
import aurelienribon.ui.css.DeclarationSetProcessor;
import aurelienribon.ui.css.Property;
import aurelienribon.ui.utils.PaintUtils;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import javax.swing.*;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Button extends JButton implements Container {
	private Color foregroundMouseDown = Color.RED;
	private Color foregroundMouseOver = Color.RED;
	private Paint stroke = Color.RED;
	private Paint strokeMouseDown = Color.RED;
	private Paint strokeMouseOver = Color.RED;
	private Paint fill = Color.RED;
	private Paint fillMouseDown = Color.RED;
	private Paint fillMouseOver = Color.RED;
	private int strokeThickness = 0;
	private int cornerRadius = 0;

	private final JLabel label = new JLabel();
	private boolean isMouseDown = false;
	private boolean isMouseOver = false;

	public Button() {
		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);

		addMouseListener(mouseAdapter);
		setOpaque(false);
	}

	@Override
	public List<?> getChildren() {
		return null;
	}

	@Override
	public void revalidate() {
		if (label == null) {super.revalidate(); return;}

		label.setFont(getFont());
		label.setText(getText());
		label.setIcon(getIcon());
		label.setHorizontalAlignment(getHorizontalAlignment());
		label.setVerticalAlignment(getVerticalAlignment());
		label.setHorizontalTextPosition(getHorizontalTextPosition());
		label.setVerticalTextPosition(getVerticalTextPosition());

		if (isMouseDown && isMouseOver) {
			label.setForeground(foregroundMouseDown);
		} else if (isMouseOver || (isMouseDown && !isMouseOver)) {
			label.setForeground(foregroundMouseOver);
		} else {
			label.setForeground(getForeground());
		}

		super.revalidate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gg = (Graphics2D) g.create();
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getWidth();
		int h = getHeight();
		double innerGap = strokeThickness / 2f;
		double innerW = w - innerGap*2 - 1;
		double innerH = h - innerGap*2 - 1;

		Shape rect = cornerRadius > 0
			? new RoundRectangle2D.Double(innerGap, innerGap, innerW, innerH, cornerRadius, cornerRadius)
			: new Rectangle2D.Double(innerGap, innerGap, innerW, innerH);

		gg.setStroke(new BasicStroke(strokeThickness));

		if (isMouseDown && isMouseOver) {
			gg.setPaint(PaintUtils.buildPaint(fillMouseDown, innerGap, innerGap, innerW, innerH));
			gg.fill(rect);
			if (strokeThickness > 0) {
				gg.setPaint(PaintUtils.buildPaint(strokeMouseDown, 0, 0, w, h));
				gg.draw(rect);
			}

		} else if (isMouseOver || (isMouseDown && !isMouseOver)) {
			gg.setPaint(PaintUtils.buildPaint(fillMouseOver, innerGap, innerGap, innerW, innerH));
			gg.fill(rect);
			if (strokeThickness > 0) {
				gg.setPaint(PaintUtils.buildPaint(strokeMouseOver, 0, 0, w, h));
				gg.draw(rect);
			}

		} else {
			gg.setPaint(PaintUtils.buildPaint(fill, innerGap, innerGap, innerW, innerH));
			gg.fill(rect);
			if (strokeThickness > 0) {
				gg.setPaint(PaintUtils.buildPaint(stroke, 0, 0, w, h));
				gg.draw(rect);
			}
		}

		gg.dispose();
	}

	private final MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			isMouseOver = true;
			revalidate();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			isMouseOver = false;
			revalidate();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				isMouseDown = true;
				revalidate();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				isMouseDown = false;
				revalidate();
			}
		}
	};

	// -------------------------------------------------------------------------
	// StyleProcessor
	// -------------------------------------------------------------------------

	public static class Processor implements DeclarationSetProcessor<Button> {
		@Override
		public void process(Button target, DeclarationSet ds) {
			Property p;

			p = AruiProperties.foregroundMouseDown;
			if (ds.contains(p)) target.foregroundMouseDown = ds.getValue(p, Color.class);
			else target.foregroundMouseDown = target.getForeground();

			p = AruiProperties.foregroundMouseOver;
			if (ds.contains(p)) target.foregroundMouseOver = ds.getValue(p, Color.class);
			else target.foregroundMouseOver = target.getForeground();

			p = AruiProperties.stroke;
			if (ds.contains(p)) target.stroke = ds.getValue(p, Paint.class);

			p = AruiProperties.strokeMouseDown;
			if (ds.contains(p)) target.strokeMouseDown = ds.getValue(p, Paint.class);
			else target.strokeMouseDown = target.stroke;

			p = AruiProperties.strokeMouseOver;
			if (ds.contains(p)) target.strokeMouseOver = ds.getValue(p, Paint.class);
			else target.strokeMouseOver = target.stroke;

			p = AruiProperties.fill;
			if (ds.contains(p)) target.fill = ds.getValue(p, Paint.class);

			p = AruiProperties.fillMouseDown;
			if (ds.contains(p)) target.fillMouseDown = ds.getValue(p, Paint.class);
			else target.fillMouseDown = target.stroke;

			p = AruiProperties.fillMouseOver;
			if (ds.contains(p)) target.fillMouseOver = ds.getValue(p, Paint.class);
			else target.fillMouseOver = target.stroke;

			p = AruiProperties.corderRadius;
			if (ds.contains(p)) target.cornerRadius = ds.getValue(p, Integer.class);

			p = AruiProperties.strokeThickness;
			if (ds.contains(p)) target.strokeThickness = ds.getValue(p, Integer.class);

			target.revalidate();
		}
	};
}
