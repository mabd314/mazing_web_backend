import React from 'react';

function CommandsHelp (props){
    return (
        <>
            <h2>Navigation</h2>
                <strong>left:</strong> The character turns left, changes orientation from north to west for example.<br/>
                <strong>right:</strong> The character turns right, changes orientation from north to east for example.<br/>
                <strong>forward:</strong> If the character is facing an open door, he walks through that door to the room behind it.<br/>
                <strong>bacward:</strong> If there is an open door behind the character, he walks through that door to the room behind it.<br/>
            <br/>
            <h2>Status</h2>
                <strong>status:</strong> Displays the character's orientation direction, gold count, and items<br/>
            <br/>
            <h2>Time</h2>
                <strong>time:</strong> Displays elapsed time since the game started as well as the remaining time you have until you finish.<br/>
            <br/>
            <h2>Looking</h2>
                <strong>look:</strong> Displays the type of wall object in front of you <em>[chest,door,painting,mirror,seller,empty wall]</em><br/>
            <br/>
            <h2>Checking</h2>
                <strong>check <em>wall_type</em>:</strong> If a wall with the type <em>wall_type</em> is in front of your character, and it can be checked, it will be checked.<br/>
                <strong>Checkable wall_types:</strong><em> [chest,door,painting,mirror]</em><br/><br/>
                <h6>Usage</h6>
                <strong>check chest:</strong> If chest is locked, it will display the name of the key needed to unlock, if it is open and has not been looted, it will be looted.<br/>
                <strong>check door:</strong> It will display the door status (locked/unlocked), and the name of the key needed to unlock if it is locked.<br/>
                <strong>check mirror/painting:</strong> It will acquire the key behind the painting/mirror if there is one.<br/>
            <br/>
            <h2>Using Items</h2>
                <strong>use <em>item_name:</em></strong> If an item with the name <em>item_name</em> is with your character, and is usable, it will be used.<br/>
                <strong>Usable item_names:</strong><em>[key[N],flashlight]</em><br/><br/>
                <h6>Usage</h6>
                <strong>use key[N]:</strong> If there is a door/chest that is locked/unlocked with the key[N] in front of your character, it will unlock/lock it. N is the keyId, key[N] becomes key5 for example.<br/>
                <strong>use flashlight:</strong> Turns the flashlight on/off, If the current room is not lit, it will become lit<br/>
            <br/>
            <h2>SwitchLights</h2>
                <strong>switchlights:</strong> If the current room has lights, it will turn the lights on/off.<br/>
            <br/>
            <h2>Trading:</h2>
                <strong>trade:</strong> If there is a seller in front of your character, it will enter trading mode with this seller, after which the following list of commands will be available:<br/><br/>
                <strong>buy <em>item_name:</em></strong> Will buy the item corresponding to <em>item_name</em> from the seller if it is listed in the seller's items, and your character has enough gold<br/>
                <strong>sell <em>item_name:</em></strong> Will sell the item corresponding to <em>item_name</em> for the seller, if it is listed in the seller's items, and your character has it<br/>
                <strong>list:</strong> Will list the seller's item again<br/>
                <strong>end:</strong> Will exit the trading mode and go back to accepting main commands
        </>
    )
}

export default CommandsHelp;