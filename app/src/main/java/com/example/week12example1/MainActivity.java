package com.example.week12example1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.week12example1.model.HockeyTeam;
import com.example.week12example1.model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<HockeyTeam> teams;
    private ArrayAdapter<HockeyTeam> teamsAdapter;
    private ListView teamsListView;

    // For demonstration purposes
    private HockeyTeam originalTeam;
    private HockeyTeam referenceCopy;
    private HockeyTeam shallowCopy;
    private HockeyTeam deepCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        teamsListView = findViewById(R.id.teams_list_view);
        Button createTeamBtn = findViewById(R.id.create_team_btn);
        Button createRefCopyBtn = findViewById(R.id.ref_copy_btn);
        Button createShallowCopyBtn = findViewById(R.id.shallow_copy_btn);
        Button createDeepCopyBtn = findViewById(R.id.deep_copy_btn);
        Button modifyTeamBtn = findViewById(R.id.modify_team_btn);
        Button modifyPlayerBtn = findViewById(R.id.modify_player_btn);
        Button sortTeamsBtn = findViewById(R.id.sort_teams_btn);

        // Initialize data
        teams = new ArrayList<>();
        teamsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teams);
        teamsListView.setAdapter(teamsAdapter);

        // Set item click listener for the list view
        teamsListView.setOnItemClickListener((parent, view, position, id) -> {
            HockeyTeam selectedTeam = teams.get(position);
            openTeamDetails(selectedTeam);
        });

        // Create Sample Team button
        createTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSampleTeam();
                updateUI();
            }
        });

        // Reference Copy button
        createRefCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createReferenceCopy();
                updateUI();
            }
        });

        // Shallow Copy button
        createShallowCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createShallowCopy();
                updateUI();
            }
        });

        // Deep Copy button
        createDeepCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDeepCopy();
                updateUI();
            }
        });

        // Modify Team button
        modifyTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyTeam();
                updateUI();
            }
        });

        // Modify Player button
        modifyPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyPlayer();
                updateUI();
            }
        });

        // Sort Teams button using anonymous Comparator
        sortTeamsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Anonymous Comparator example
                teams.sort(new Comparator<HockeyTeam>() {
                    @Override
                    public int compare(HockeyTeam team1, HockeyTeam team2) {
                        return team1.getTeamName().compareTo(team2.getTeamName());
                    }
                });
                updateUI();

                // Show toast using anonymous Runnable
                final String message = "Teams sorted alphabetically!";
                new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }.run();
            }
        });
    }

    private void createSampleTeam() {
        originalTeam = new HockeyTeam("Maple Leafs", 1917);
        originalTeam.setWins(30);
        originalTeam.setLosses(15);

        originalTeam.addPlayer(new Player("Matthews", 34, "Forward"));
        originalTeam.addPlayer(new Player("Marner", 16, "Forward"));
        originalTeam.addPlayer(new Player("Rielly", 44, "Defense"));

        // Get first player and set some stats
        if (!originalTeam.getPlayers().isEmpty()) {
            Player player = originalTeam.getPlayers().get(0);
            player.setGoals(50);
            player.setAssists(40);
        }

        teams.add(originalTeam);
        Toast.makeText(this, "Sample team created!", Toast.LENGTH_SHORT).show();
    }

    private void createReferenceCopy() {
        if (originalTeam == null) {
            Toast.makeText(this, "Create a sample team first!", Toast.LENGTH_SHORT).show();
            return;
        }

        // This is a reference copy
        referenceCopy = originalTeam;
        referenceCopy.setTeamName("Reference Copy");

        teams.add(referenceCopy);
        Toast.makeText(this, "Reference copy created!", Toast.LENGTH_SHORT).show();
    }

    private void createShallowCopy() {
        if (originalTeam == null) {
            Toast.makeText(this, "Create a sample team first!", Toast.LENGTH_SHORT).show();
            return;
        }

        // This is a shallow copy
        shallowCopy = originalTeam.clone();
        shallowCopy.setTeamName("Shallow Copy");

        teams.add(shallowCopy);
        Toast.makeText(this, "Shallow copy created!", Toast.LENGTH_SHORT).show();
    }

    private void createDeepCopy() {
        if (originalTeam == null) {
            Toast.makeText(this, "Create a sample team first!", Toast.LENGTH_SHORT).show();
            return;
        }

        // This is a deep copy
        deepCopy = originalTeam.deepCopy();
        deepCopy.setTeamName("Deep Copy");

        teams.add(deepCopy);
        Toast.makeText(this, "Deep copy created!", Toast.LENGTH_SHORT).show();
    }

    private void modifyTeam() {
        if (originalTeam == null) {
            Toast.makeText(this, "Create a sample team first!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Modify the original team - this will affect reference copies
        originalTeam.setWins(originalTeam.getWins() + 1);

        Toast.makeText(this, "Added a win to original team!", Toast.LENGTH_SHORT).show();
    }

    private void modifyPlayer() {
        if (originalTeam == null || originalTeam.getPlayers().isEmpty()) {
            Toast.makeText(this, "Create a sample team first!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Modify the first player of the original team
        // This will affect shallow copies as well as reference copies
        Player player = originalTeam.getPlayers().get(0);
        player.setGoals(player.getGoals() + 1);

        Toast.makeText(this, "Added a goal to " + player.getName() + "!", Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {
        teamsAdapter.notifyDataSetChanged();
    }

    private void openTeamDetails(HockeyTeam team) {
        Intent intent = new Intent(this, TeamDetailActivity.class);
        intent.putExtra("team", team);
        startActivity(intent);
    }
}