package apachecms.ar.com.unimedicalapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import apachecms.ar.com.unimedicalapp.Data.model.Lender;
import apachecms.ar.com.unimedicalapp.R;

public class LenderListAdapter extends ArrayAdapter<Lender> {

    private Context mContext;
    private int mResource;

    public LenderListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Lender> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName =  convertView.findViewById(R.id.textView1);
        TextView tvEmail =  convertView.findViewById(R.id.textView2);
        TextView tvPhone =  convertView.findViewById(R.id.textView3);

        tvName.setText(getItem(position).getName());
        tvEmail.setText(getItem(position).getEmail());
        tvPhone.setText(getItem(position).getPhone());

        return convertView;
    }
}
