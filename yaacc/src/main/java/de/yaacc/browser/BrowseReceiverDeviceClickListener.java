/*
 * Copyright (C) 2014 Tobias Schoene www.yaacc.de
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package de.yaacc.browser;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import org.fourthline.cling.model.meta.Device;

import de.yaacc.R;
import de.yaacc.upnp.UpnpClient;

/**
 * @author Tobias Schoene (the openbit)
 */
public class BrowseReceiverDeviceClickListener implements View.OnClickListener {
    BrowseReceiverDeviceAdapter adapter;
    RecyclerView recyclerView;
    UpnpClient upnpClient;

    public BrowseReceiverDeviceClickListener(RecyclerView recyclerView, BrowseReceiverDeviceAdapter adapter, UpnpClient upnpClient) {
        this.upnpClient = upnpClient;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
    }

    @Override
    public void onClick(View itemView) {
        CheckBox checkBox = (CheckBox) itemView
                .findViewById(R.id.browseReceiverDeviceItemCheckbox);
        Device<?, ?, ?> device = (Device<?, ?, ?>) adapter.getItem(recyclerView.getChildAdapterPosition(itemView));
        if (checkBox.isChecked()) {
            Log.d(getClass().getName(), "isChecked:" + device.getDisplayString());
            adapter.removeSelectedDevice(device);
            upnpClient.removeReceiverDevice(device);
            checkBox.setChecked(false);
        } else {
            Log.d(getClass().getName(), "isNotChecked:" + device.getDisplayString());
            adapter.addSelectedDevice(device);
            upnpClient.addReceiverDevice(device);
            checkBox.setChecked(true);
        }
    }
} 