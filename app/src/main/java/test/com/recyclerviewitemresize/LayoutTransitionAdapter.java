package test.com.recyclerviewitemresize;

import android.animation.LayoutTransition;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class LayoutTransitionAdapter extends RecyclerView.Adapter<LayoutTransitionAdapter.MainViewHolder> {

    static final Random random = new Random(System.currentTimeMillis());

    public static class ColorName {
        final String name;
        final int color;

        public ColorName(String name) {
            this.name = name;
            this.color = Color.rgb(128 + random.nextInt(128), 128 + random.nextInt(128), 128 + random.nextInt(128));
        }
    }

    final ColorName[] contacts = new ColorName[]{
            new ColorName("Amanda M. Ormond"),
            new ColorName("Anquises Mejia Bustos"),
            new ColorName("Bellisima Goodchild"),
            new ColorName("Bodo Greenhand"),
            new ColorName("Brogan Allan"),
            new ColorName("Claudia Bosch"),
            new ColorName("Elisa Asmara"),
            new ColorName("Emile Barrientos"),
            new ColorName("Ermes Toscano"),
            new ColorName("Guarino Romani"),
            new ColorName("Heike Dresner"),
            new ColorName("Isobel Chamberlain"),
            new ColorName("Ja'rod Kahnrah"),
            new ColorName("Jessica L. Carrillo"),
            new ColorName("Joseph M. Parks"),
            new ColorName("Kabarann D'Ghor"),
            new ColorName("Karol Perea Paredes"),
            new ColorName("Kotkhe Varrin"),
            new ColorName("Kiera Pritchard"),
            new ColorName("Lavinia Sackville-Baggins"),
            new ColorName("Manville Dubois"),
            new ColorName("Marion Deslauriers"),
            new ColorName("Mewael Semere"),
            new ColorName("Negassi Girmay"),
            new ColorName("Numilen Sarabia Solano"),
            new ColorName("Ovidio Colombo"),
            new ColorName("Simone Hahn"),
            new ColorName("Stanley Ross"),
            new ColorName("Spencer Porter"),
            new ColorName("Tekle Ambessa"),
            new ColorName("Yasmin Alexander")
    };

    int opened = -1;

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int pos) {
        return new MainViewHolder((ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int pos) {
        final ColorName contact = contacts[pos];
        holder.bind(pos, contact.name, contact.color);
    }

    @Override
    public int getItemCount() {
        return contacts.length;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView contactNameTV;
        public final TextView infos;

        public MainViewHolder(ViewGroup itemView) {
            super(itemView);
            contactNameTV = ((TextView) itemView.findViewById(R.id.contactName));
            infos = ((TextView) itemView.findViewById(R.id.infos));

            itemView.setOnClickListener(this);
        }

        public void bind(int pos, String name, int color) {
            contactNameTV.setText(name);

            setVisibility(pos == opened, false);

            itemView.setBackgroundColor(color);
        }

        private void setVisibility(boolean opened, boolean animate) {
            if (animate) {
                setIsRecyclable(false);
                final LayoutTransition layoutTransition = new LayoutTransition();
                layoutTransition.setDuration(1500);
                layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
                ((ViewGroup) itemView).setLayoutTransition(layoutTransition);

                itemView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((ViewGroup) itemView).setLayoutTransition(null);
                        setIsRecyclable(true);
                    }
                }, 2000);
            }

            if (opened)
                infos.setVisibility(View.VISIBLE);
            else
                infos.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View v) {
            if (opened == getPosition()) {
                opened = -1;
                setVisibility(false, true);
            }
            else {
                int oldOpened = opened;
                opened = getPosition();
                setVisibility(true, true);

                final MainViewHolder oldHolder = (MainViewHolder) ((RecyclerView) itemView.getParent()).findViewHolderForPosition(oldOpened);
                if (oldHolder != null)
                    oldHolder.setVisibility(false, true);
            }
        }
    }
}
