package com.eowise.recyclerview.stickyheaders.samples.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eowise.recyclerview.stickyheaders.samples.R;
import com.eowise.recyclerview.stickyheaders.samples.listeners.OnRemoveListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by aurel on 22/09/14.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements OnRemoveListener {

    private List<String> items;

    public PersonAdapter() {
        items = new ArrayList<String>(Arrays.asList(persons));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(itemView, this);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.label.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onRemove(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public List<String> getItems() {
        return items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView label;
        private OnRemoveListener listener;

        public ViewHolder(View itemView, OnRemoveListener listener) {
            super(itemView);
            this.label = (TextView) itemView.findViewById(R.id.name);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onRemove(getPosition());
        }
    }


    private static String[] persons = {
            "Abram Tavernia",
            "Alexa Oquin",
            "Alvin Lainez",
            "Alyce Rakestraw",
            "Angel Scruggs",
            "Annabel Wardle",
            "Ardella Hollinger",
            "Arlean Drewes",
            "Armida Carasco",
            "Asa Modeste",
            "Ashlea Aguillard",
            "Aurore Maris",
            "Bao Godbold",
            "Bettye Wenger",
            "Bill Thatch",
            "Brad Amis",
            "Bridget Goulette",
            "Bryan Rarick",
            "Camie Malcolm",
            "Caridad Nesbitt",
            "Carleen Maul",
            "Carmelo Ehrmann",
            "Caroll Ruben",
            "Cherryl Suter",
            "Christeen Bonner",
            "Christene Thrailkill",
            "Cindie Luong",
            "Claudio Llanos",
            "Cleveland Selvage",
            "Clint Cullen",
            "Clora Graybeal",
            "Cristin Culton",
            "Crysta Bolt",
            "Cuc Hetzel",
            "Daine Cumbie",
            "Danuta Villalta",
            "Darci Quick",
            "Darius Hermes",
            "Delaine Evins",
            "Delpha Godin",
            "Dexter Bencomo",
            "Dione Rhines",
            "Donella Blumstein",
            "Dorene Kisling",
            "Dudley Benavides",
            "Dulce Demille",
            "Ebonie Wallis",
            "Effie Wiley",
            "Elayne Munro",
            "Elisha Funches",
            "Elna Padua",
            "Emmy Denk",
            "Farrah Delosantos",
            "Frieda Buesing",
            "Gilda Tse",
            "Gina Dufault",
            "Giovanna Schepis",
            "Glayds Mcguigan",
            "Glinda Dunagan",
            "Gwenda Fraser",
            "Hai Oday",
            "Halley Holscher",
            "Hellen Baillie",
            "Herbert Renninger",
            "Hobert Yopp",
            "Hollis Haubert",
            "Hui Lupien",
            "Ileen Mccasland",
            "Imelda Moser",
            "Ione Littlewood",
            "Jacalyn Gressett",
            "Jacquelyn Butter",
            "Jade Churchwell",
            "Jami Selph",
            "Janeth Ringwood",
            "Jeffry Carcamo",
            "Jerlene Zellers",
            "Jerome Tomko",
            "Jerrod Rother",
            "Jettie Conner",
            "Joaquin Keplinger",
            "Joette Healey",
            "Jorge Molina",
            "Juana Olds",
            "Jules Friley",
            "Julio Krier",
            "Kareen Bergey",
            "Katharyn Doten",
            "Katherine Ragsdale",
            "Kathryn Edgington",
            "Katia Hock",
            "Keeley Pass",
            "Kendrick Moncada",
            "Kenyetta Wick",
            "Kimber Boulware",
            "Kitty Manthe",
            "Kristan Blake",
            "Lakeisha Medlin",
            "Lakesha Voth",
            "Lanora Pair",
            "Lashon Abramson",
            "Laurie Campa",
            "Laurinda Barcus",
            "Lavern Puig",
            "Lera Mckibben",
            "Long Show",
            "Louanne Garling",
            "Louella Petillo",
            "Lucinda Sockwell",
            "Lyla Fitzsimons",
            "Mackenzie Ooten",
            "Malia Claiborne",
            "Manie Yarberry",
            "Marchelle Halcomb",
            "Marcie Augusta",
            "Marguerita Tenaglia",
            "Mari Sheperd",
            "Mariela Ruggieri",
            "Marielle Connolly",
            "Marilyn Franck",
            "Marisol Marmolejo",
            "Marth Pitchford",
            "Marty Cobey",
            "Maximo Thornburg",
            "Meggan Plumadore",
            "Mellissa Schnitzer",
            "Melodie Kitch",
            "Mimi File",
            "Mina Nolte",
            "Mira Archuleta",
            "Modesto Higgenbotham",
            "Mohammed Orr",
            "Morgan Maddy",
            "Morgan Mensch",
            "Moriah Grubb",
            "Nedra Dyson",
            "Norene Nelms",
            "Odis Mill",
            "Ok Hutter",
            "Olin Bolander",
            "Otilia Dejulio",
            "Otis Shore",
            "Patrina Crystal",
            "Philip Wengert",
            "Porter Ketner",
            "Rhiannon Lavoie",
            "Richard Domingues",
            "Rochell Molock",
            "Rosalva Gutman",
            "Rosalyn Pesce",
            "Rosaria Rosengarten",
            "Roxane Clayborn",
            "Rozanne Mahaney",
            "Sal Wilkinson",
            "Saundra Lundahl",
            "Scotty Ralph",
            "Seema Boots",
            "Selena Eisenhower",
            "Shae Hellard",
            "Shae Latz",
            "Shantay Wilcox",
            "Shawnda Kees",
            "Shayne Cutler",
            "Shenita Cassette",
            "Sherie Culp",
            "Sherrie Poole",
            "Shirley Cliett",
            "Shizue Alcaraz",
            "Sid Streets",
            "Stacia Twellman",
            "Stasia Slay",
            "Stephen Eagles",
            "Svetlana Hallam",
            "Tambra Buchner",
            "Tamie Branham",
            "Tammera Hutt",
            "Tawanna Rameriz",
            "Tawna Seim",
            "Terisa Whitbeck",
            "Terresa Brantley",
            "Terri Barnaby",
            "Tinisha Gammill",
            "Todd Netter",
            "Toshiko Skowron",
            "Traci Schurr",
            "Trish Perino",
            "Tyesha Bruemmer",
            "Valda Skyles",
            "Vella Montilla",
            "Venita Richarson",
            "Vera Noffsinger",
            "Vinnie Gobeil",
            "Waltraud Nelsen",
            "Wendy Zachery",
            "Willard Qualls",
            "Willetta Zucker",
            "Yen Staton",
            "Yolonda Hadnott",
            "Yoshie Califano",
            "Yu Schilke"
    };
}
