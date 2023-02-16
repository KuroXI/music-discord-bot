# Music Discord Bot
![forthebadge](http://forthebadge.com/images/badges/made-with-java.svg)
![forthebadge](http://forthebadge.com/images/badges/built-with-love.svg)
<br />

## About the project
A Discord bot that play music using [JDA](https://github.com/DV8FromTheWorld/JDA) and [lavaplayer-fork](https://github.com/Walkyst/lavaplayer-fork).

## Update
  - **1.1.0**
    - Spotify Support

## Features
- Basic Commands of Music Bot
- Auto Disconnect
  - When the bot has not played music for 5 minutes
  - When the bot is alone in the voice channel. It also pauses the song.

## Commands
- The `/play` command allows the user to play music.
  - Needs to be in the voice channel
  - If the bot is in the voice channel, It needs to be on the same voice channel.
- The `/stop` command stops the entire music.
- The `/pause` command pauses the current song that is playing.
- The `/resume` command resumes the current song if it's paused.
- The `/skip` command skips the current song if there's one.
- The `/shuffle` command shuffles the queue.

## Getting Started
1. Clone the repository:
```bash
git clone https://github.com/KuroXI/music-discord-bot.git
```

2. Change all the value in the `.env` file\
Get the spotify `Client ID` and `Client Key` [here](https://developer.spotify.com/dashboard)
```env
TOKEN=
SPOTIFY_ID=
SPOTIFY_KEY=
```

## Contributing
I'm open to contributions to this project. If you have an idea for a new feature or find a bug, please open an issue or submit a pull request.

## License
This project is licensed under the MIT License. See `LICENSE.txt` for more details.