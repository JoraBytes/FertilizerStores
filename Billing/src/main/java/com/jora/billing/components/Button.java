package com.jora.billing.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

public class Button extends JButton implements FocusListener, MouseListener {
	private static final long serialVersionUID = 1L;

	private Color hoverBackgroundColor;

	private Color pressedBackgroundColor;

	public Button() {
		initialize();
	}

	public Button(Action arg0) {
		super(arg0);
		initialize();
	}

	public Button(Icon arg0) {
		super(arg0);
		initialize();
	}

	public Button(String arg0) {
		super(arg0);
		initialize();
	}

	public Button(String arg0, Icon arg1) {
		super(arg0, arg1);
		initialize();
	}

	private void initialize() {
		setContentAreaFilled(false);
		setFocusPainted(false);
		addFocusListener(this);
		addMouseListener(this);
	}

	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			if (this.pressedBackgroundColor != null)
				g.setColor(this.pressedBackgroundColor);
		} else if (getModel().isRollover()) {
			if (this.hoverBackgroundColor != null)
				g.setColor(this.hoverBackgroundColor);
		} else {
			g.setColor(getBackground());
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public Color getHoverBackgroundColor() {
		return this.hoverBackgroundColor;
	}

	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}

	public Color getPressedBackgroundColor() {
		return this.pressedBackgroundColor;
	}

	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
		this.pressedBackgroundColor = pressedBackgroundColor;
	}

	public void setBackground(Color bg) {
		this.pressedBackgroundColor = bg;
		this.hoverBackgroundColor = bg;
		super.setBackground(bg);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				BorderFactory.createLineBorder(Color.WHITE, 1)));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isFocusOwner())
			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
					BorderFactory.createLineBorder(Color.WHITE, 0)));
	}

	@Override
	public void focusGained(FocusEvent e) {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
				BorderFactory.createLineBorder(Color.WHITE, 1)));
	}

	@Override
	public void focusLost(FocusEvent e) {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createLineBorder(Color.WHITE, 0)));
	}

}
