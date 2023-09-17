import java.util.*;

public class GhostRegister {

    private Set<Ghost> ghosts;
    

    public GhostRegister() {
        ghosts = new HashSet<>();
    }

    public boolean addGhost(Ghost ghost) {
        if (ghost == null || ghost.getName() == null) {
            return false;
        }

        return ghosts.add(ghost);
    }

    public Collection<Ghost> getAllGhosts() {
        return new HashSet<>(ghosts);
    }

    public String getTestVariant() {
       return "G32";
    }
    public int removeInactiveEvilGhosts() {
      int ghostSizeBeforeRemove = ghosts.size();
      ghosts.forEach(ghost -> {
          if (ghost.isBad() && ghost.isNotMaterialized()) {
              ghosts.remove(ghost);
    };
return ghostSizeBeforeRemove - ghost.size();

});
}
}

