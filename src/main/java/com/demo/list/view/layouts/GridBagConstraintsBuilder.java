package com.demo.list.view.layouts;

import java.awt.*;

public class GridBagConstraintsBuilder {

    public static GridBagConstraints constraints(
            int gridx,
            int gridy,
            int gridwidth,
            int gridheight,
            int fill,
            double weightx,
            double weighty
    ) {
        var constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = fill;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        return constraints;
    }

}