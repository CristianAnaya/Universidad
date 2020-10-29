package co.edu.uac.apmoviles.sqliteuniversidad.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.edu.uac.apmoviles.sqliteuniversidad.Estudiante;
import co.edu.uac.apmoviles.sqliteuniversidad.R;

public class DescargosAdapter extends BaseAdapter implements Filterable {

    private Context context;
    List<Estudiante> mData;
    private List<Estudiante> descargoList;
    Calendar calendar =  Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = format.format(calendar.getTime());
    ValueFilter valueFilter;

    public DescargosAdapter(Context context, List<Estudiante> descargoList) {
        this.context = context;
        this.descargoList = descargoList;
        this.mData = descargoList;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_trabajos,null,true);
        TextView txtCodigo = view.findViewById(R.id.txtCodigo);
        TextView txtPrograma = view.findViewById(R.id.txtPrograma);
        TextView txtInternet = view.findViewById(R.id.txtInternet);
        TextView txtComputadora = view.findViewById(R.id.txtComputadora);
        TextView txtTelefono = view.findViewById(R.id.txtTelefono);



        txtCodigo.setText(descargoList.get(position).getCodigo());
        txtPrograma.setText(descargoList.get(position).getPrograma());
        txtInternet.setText(descargoList.get(position).getInternet());
        txtComputadora.setText(descargoList.get(position).getComputadora());
        txtTelefono.setText(descargoList.get(position).getTelefono());
        return view;

    }

    @Override
    public int getCount() {
        return descargoList.size();
    }

    @Nullable
    @Override
    public Estudiante getItem(int position) {
        return descargoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getItemCount() {
        return descargoList == null ? 0 : descargoList.size();
    }

    public boolean empty() {
        return getItemCount() == 0;
    }




    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Estudiante> filterList = new ArrayList<>();
                for (int i = 0; i < descargoList.size(); i++) {
                    if ((descargoList.get(i).getCodigo().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(descargoList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = descargoList.size();
                results.values = descargoList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List<Estudiante>) results.values;
            notifyDataSetChanged();
        }

    }
}

