const gulp = require('gulp')
const babel = require('gulp-babel')
const electron = require('electron-connect').server.create()


const babelconfig = {
    "presets": [
        "@babel/preset-env",
        "@babel/preset-react"
    ]
}

function buildJsx(cb) {
    gulp.src('src/**/*.js')
        .pipe(babel(babelconfig))
        .pipe(gulp.dest('out'))
    cb()
}

function moveHtml(cb) {
    gulp.src([
        'src/**/*.+(js|html|css)',
        '!src/**/*.jsx',
        '!src/**/*.less',
        '!src/server/**/*'
    ]).pipe(gulp.dest('out'))
    cb()
}

function startElectron(cb) {
    electron.start()
}

exports['build-jsx'] = buildJsx
exports['start'] = gulp.series(buildJsx, moveHtml, startElectron)

