package com.mazing.map;
import com.mazing.game.*;
import com.mazing.item.*;
import com.mazing.command.*;
import com.mazing.wall.*;

import java.io.FileNotFoundException;

public interface Map {

  void setUpRooms(Game game);

  void setUpCharacter(Game game);

}
