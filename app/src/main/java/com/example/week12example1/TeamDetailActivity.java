package com.example.week12example1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.week12example1.model.HockeyTeam;
import com.example.week12example1.model.Player;

public class TeamDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        // Get team from intent
        HockeyTeam team = (HockeyTeam) getIntent().getSerializableExtra("team");

        if (team == null) {
            finish();
            return;
        }

        // Initialize views
        TextView teamNameText = findViewById(R.id.team_name_text);
        TextView teamDetailsText = findViewById(R.id.team_details_text);
        ListView playersList = findViewById(R.id.players_list_view);

        // Set team details
        teamNameText.setText(team.getTeamName());
        teamDetailsText.setText(String.format("Founded: %d\nRecord: %d-%d\nTotal Players: %d",
                team.getFoundingYear(), team.getWins(), team.getLosses(), team.getPlayers().size()));

        // Set up players list
        ArrayAdapter<Player> playersAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, team.getPlayers());
        playersList.setAdapter(playersAdapter);
    }
}