# Bloggit

Bloggit is a `Electron` + `React` app intended to use git as a database of markdown texts and show them in a "blogish" way.

## Origin

Our team needed a place to store and share sensitive and/or private knowledge and stumbled on bureaucracy barriers, which made having an internal host or paying for 3rd-party solutions unfeasible.

So, we came out with the idea of using a private git repository, which was already available, as a database of our data.

It also turns out as a good challenge for us. :)

## Structure

This project was initially bootstrapped with [Create React App](https://github.com/facebook/create-react-app), but since git commands run in Electron main process we should move soon to a customized build approach.

## How to start app

In the project directory, run:

### `npm install`

Install project dependencies locally.

### `npm run start-server`

Starts nodejs server which is going to run the react app in the development mode.

### `npm start`

In another terminal screen, run `npm start` to start Electron app.

The page will reload if you make edits on React portion. React logs are printed on browser console, to see them go to *View -> Toggle Developer Mode*.

### `npm run debug`

If you want to see logs from Electron main process, please start it as `npm run debug`.
