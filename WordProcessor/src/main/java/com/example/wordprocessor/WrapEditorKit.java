package com.example.wordprocessor;

import javax.swing.text.*;

public class WrapEditorKit extends StyledEditorKit {

    // Default factory for creating views
    private ViewFactory defaultFactory = new WrapColumnFactory();

    @Override
    /********************
     * Pre: None
     * Post: Returns the default view factory used by this editor kit
     ********************/
    public ViewFactory getViewFactory() {
        return defaultFactory;
    }
}

class WrapColumnFactory implements ViewFactory {

    @Override
    /********************
     * Pre: None
     * Post: Creates and returns a view based on the element's type
     * @param elem The element for which a view is created
     * @return The created view based on the element type
     ********************/
    public View create(Element elem) {
        String kind = elem.getName(); // Get the type of the element

        // Check the type and create corresponding views
        if (kind != null) {
            if (kind.equals(AbstractDocument.ContentElementName)) {
                return new WrapLabelView(elem); // WrapLabelView for content elements
            } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                return new ParagraphView(elem); // ParagraphView for paragraph elements
            } else if (kind.equals(AbstractDocument.SectionElementName)) {
                return new BoxView(elem, View.Y_AXIS); // BoxView for section elements
            } else if (kind.equals(StyleConstants.ComponentElementName)) {
                return new ComponentView(elem); // ComponentView for component elements
            } else if (kind.equals(StyleConstants.IconElementName)) {
                return new IconView(elem); // IconView for icon elements
            }
        }

        // Default view is LabelView for unknown elements
        return new LabelView(elem);
    }
}

class WrapLabelView extends LabelView {

    /********************
     * Pre: None
     * Post: Constructs a WrapLabelView with the specified element
     * @param elem The element for which this view is created
     ********************/
    public WrapLabelView(Element elem) {
        super(elem);
    }

    @Override
    /********************
     * Pre: None
     * Post: Returns the minimum span of the view along the specified axis
     * @param axis The axis (X_AXIS or Y_AXIS) for which the minimum span is calculated
     * @return The minimum span of the view along the specified axis
     * @throws IllegalArgumentException If the axis parameter is invalid
     ********************/
    public float getMinimumSpan(int axis) {
        switch (axis) {
            case View.X_AXIS:
                return 0; // Minimum width is 0 for X_AXIS
            case View.Y_AXIS:
                return super.getMinimumSpan(axis); // Default behavior for Y_AXIS
            default:
                throw new IllegalArgumentException("Invalid axis: " + axis);
        }
    }
}
